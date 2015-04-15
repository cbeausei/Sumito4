package axelindustry.sumito4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Canvas canvas = new Canvas();
    /* The following constant values will be used in the coordinates formulae
    /!\ THE TERM "RELATIVE" MUST BE UNDERSTOOD AS "RELATIVELY TO THE HEIGHT OF THE BOARD IMAGE" /!\
    rel_span_x indicates the relative space between a position and its nearest lateral neighbour
    rel_offset_x indicates the relative offset of the board in the picture, that is the lateral offset of the extreme left position
    rel_b_h indicates the relative ball height ; it is also rhe relative ball width
    rel_span_y indicates the relative distance between two lines
    /!\ rel_span_y is not a distance between neighbour positions /!\
    rel_offset_y indicates the relative offset of the board in the picture, that is the vertical offset of the extreme top line

     */
    double rel_span_x = 45/516.0, rel_offset_x = 29/129.0, rel_b_h = 1/12.0, rel_span_y = 78/8.0/129.0, rel_offset_y = 26/129.0;
    /* To begin with, we will consider 14 black and 14 white balls.
    the picture of a white ball will be stored in memory using bouleBlanche, then every member of boulesBlanches will be a resized copy of bouleBlanche
    it works the same for black balls, of course
     */
    Bitmap boulesNoires[], boulesBlanches[], plateau, fond, bouleNoire, bouleBlanche, bouleBleue;
    /* posYn contains the y-coordinates of the black balls
    posXn their x-coordinates
    posYb and posXb are the equivalent for white balls
    all coordinates are expressed as follows: (colNum, rowNum)
    colNum being the number of the column in the coordinates defined on the figure that was uploaded on the drive
    rowNum being the number of the line in this system
     */
    int[] posYn = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2};
    int[] posXn = new int[]{4, 5, 6, 7, 8, 3, 4, 5, 6, 7, 8, 4, 5, 6};
    int[] posYb = new int[]{8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 6, 6, 6};
    int[] posXb = new int[]{0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 2, 3, 4};
    // the boolean selection will help us detect the phase during which the user is selecting the balls he wants to move
    boolean selection = false;
    // x and y will contain the coordinates of any user event. For some weired reason, these coordinates are not integers...
    float x = 10, y = 20;
    /* w and h will match the dimensions of the screen
    width and height those of the board picture
    evidently, width <= w and height <= h
    There is always at least one case of equality, rarely both
     */
    int w = 0, h = 0, width, height;


    public DrawView(Context context) {
        super(context);
        // let's store the bitmaps in memory
        bouleNoire = BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire);
        bouleBlanche = BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche);
        bouleBleue = BitmapFactory.decodeResource(getResources(), R.drawable.boulebleue);

        // we then pre-allocate the bitmap vectors, so as not to overload the memory while drawing
        boulesNoires = new Bitmap[14];
        boulesBlanches = new Bitmap[14];

        // the board and background are stored too
        plateau = BitmapFactory.decodeResource(getResources(), R.drawable.cadre);
        fond = BitmapFactory.decodeResource(getResources(), R.drawable.fond);
    }

    /* convertCoordinates(X, Y) returns [x, y] where:
        - X is the column number in the game grid
        - Y is the row number in the game grid
        - (x, y) is the pixel matching the centre of the position (X, Y) on the screen
     */
    private int[] convertCoordinates(int x, int y){
        int absc = (int)(((x+y/2.0-2.0)*rel_span_x+rel_offset_x-rel_b_h/2)*height + (w-width)/2.0);
        y = (int)((y*rel_span_y+rel_offset_y-rel_b_h/2)*height + (h-height)/2.0);
        x = absc;
        return(new int[]{x, y});
    }

    /* revertCoordinates(x, y) returns [X, Y] where:
        - (x, y) is a pixel on the screen
        - (X, Y) is the position in the grid whiches representation on the screen is the closest to (x, y)
     */
    private int[] revertCoordinates(int x, int y){
        y = (int)(1/rel_span_y/height*(y +(height-h)/2 +rel_b_h*height/2-rel_offset_y*height));
        x = (int)(1/rel_span_x/height*(x + (width-w)/2 +rel_b_h*height/2-rel_offset_x*height)+2-y/2);
        return(new int[]{x, y});
    }

    /* NB: - applying convertCoordinates to the result of revertCoordinates has an effect of magnetic grid:
             the pixel is converted into the closest pixel that represents a position
           - applying revertCoordinates to the result of convertCoordinates has no effect
     */

    // dimensions() computes the dimensions of the displayed objects whenever the size of the screen varies
    private void dimensions(){
        h = canvas.getHeight();
        w = canvas.getWidth();

        // we compute the optimal dimensions of the board image: it must be as large as possible, but not outspan the screen
        if (1290 * w < 1487 * h) {
            width = w;
            height = 1290 * w / 1487;
        } else {
            height = h;
            width = 1487 * h / 1290;
        }
        // once done, we draw accordingly
        plateau = Bitmap.createScaledBitmap(plateau, width, height, true);

        // the background is easier to draw: there must just be no hole
        fond = Bitmap.createScaledBitmap(fond, Math.max(w, h), Math.max(w, h), true);

        // we draw the balls proportionally to the height of the board
        for(int i = 0 ; i < 14 ; i++) {
            boulesNoires[i] = Bitmap.createScaledBitmap(bouleNoire, height / 12, height / 12, true);
            boulesBlanches[i] = Bitmap.createScaledBitmap(bouleBlanche, height / 12, height / 12, true);
        }
        bouleBleue = Bitmap.createScaledBitmap(bouleBleue, height / 12, height / 12, true);
    }

    /* the core of the drawing phase happens here:
    onDraw is called whenever something has changed, have it an actual influence on the drawing or not, eg:
     - when the screen is rotated
     - when invalidate() is called, hence when the user touches the screen
     */
    @Override
    public void onDraw(final Canvas canvas) {
        this.canvas = canvas;

        // h contains the previously-computed height of the screen. There is no need to re-compute all dimensions unless this height has changed
        if(h != canvas.getHeight()){
            this.dimensions();
        }

        // OK, the dimensions are fine, we can draw
        canvas.drawBitmap(fond, 0, 0, null);
        canvas.drawBitmap(plateau, (w - width) / 2, (h - height) / 2, null);
        for(int i = 0 ; i < 14 ; i++) {
            // We have the coordinates of the balls in the game grid. We must compute the coordinates of their representation on the screen
            int coordinatesn[];
            int coordinatesb[];
            coordinatesn = convertCoordinates(posXn[i], posYn[i]);
            coordinatesb = convertCoordinates(posXb[i], posYb[i]);

            // Now we can draw them
            canvas.drawBitmap(boulesNoires[i], coordinatesn[0], coordinatesn[1], null);
            canvas.drawBitmap(boulesBlanches[i], coordinatesb[0], coordinatesb[1], null);

            // In case the user is selecting, we must round the position of their finger to the nearest ball position
            if(selection) {
                int[] coordinatesSelection = revertCoordinates((int) x, (int) y);
                coordinatesSelection = convertCoordinates(coordinatesSelection[0], coordinatesSelection[1]);
                canvas.drawBitmap(bouleBleue, coordinatesSelection[0], coordinatesSelection[1], null);
            }
        }
    }

    // the following method handles all touch events coming from the user
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // ok, an event has occured. Let's locate it first
        x = event.getRawX() - h/24;
        y = event.getRawY() - h/24;

        // We must react accordingly: while the finger is down, we are in a phase of selection
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            selection = true;
        }

        // as soon as the finger is up, the selection is over
        else if(event.getAction() == MotionEvent.ACTION_UP){
            selection = false;
        }

        this.invalidate();
        return true;
    }
}