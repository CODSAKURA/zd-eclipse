package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *  This file can be used to create very simple animations.  Just fill in
 *  the definition of drawFrame with the code to draw one frame of the 
 *  animation, and possibly change a few of the values in the rest of
 *  the program as noted below.
 */
public class Main extends Application {

    /**
     * Draws one frame of an animation. This subroutine should be called
     * about 60 times per second.  It is responsible for redrawing the
     * entire drawing area. The parameter g is used for drawing. The frameNumber 
     * starts at zero and increases by 1 each time this subroutine is called.  
     * The parameter elapsedSeconds gives the number of seconds since the animation
     * was started.  By using frameNumber and/or elapsedSeconds in the drawing
     * code, you can make a picture that changes over time.  That's an animation.
     * The parameters width and height give the size of the drawing area, in pixels.  
     */
    public void drawFrame(GraphicsContext g, int frameNumber, double elapsedSeconds, int width, int height) {
    	
    	/*First, fill the entire image with a background color!!
    	 * Without these two lines, the previous pattern draw on the screen will still exist!!
    	 * These two lines like refresh the background with white color.
    	 * */
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height); 
        
        /*-----------------------------------------------------------------------------------------------------*/
    	
    	/*Variable(frameNumber) keep increasing the value from 0 to unlimited.
    	 * Therefore, variable(cyclicFrameNumber) will be 0 to 320 then 0 to 320 then 0 to 320 and so on.
    	 * Because:
    	 * 		0 % 320 = 0; 1 % 320 = 1;............;319 % 320 = 319;
    	 * 		320 % 320 = 0; 321 % 320 = 1;...........;639 % 320 = 319;
    	 * 		640 % 320 = 0; 641 % 320 = 1;....................
    	 * */
    	int cyclicFrameNumber1 = frameNumber % width;
    	
    	/*Draw the red square in a new position.
    	 * CAUTION: Because the previous red square will still exit on the screen;
    	 * we need the top two line codes: 
    	 *         g.setFill(Color.WHITE);
         *         g.fillRect(0, 0, width, height); 
    	 * to refresh the background with white colour.
    	 * */
    	g.setFill(Color.RED);
    	g.fillRect(cyclicFrameNumber1, 0, 20, 20);
    	
    	/*Green square run in a quicker speed than the red*/
    	int cyclicFrameNumber2 = frameNumber * 3 % width;
    	g.setFill(Color.GREEN);
    	g.fillRect(cyclicFrameNumber2, 20, 20, 20);
    	
    	/*Blue square run in the most quickest speed among three colour square*/
    	int cyclicFrameNumber3 = frameNumber * 5 % width;
    	g.setFill(Color.BLUE);
    	g.fillRect(cyclicFrameNumber3, 40, 20, 20);
    	
    	/*-----------------------------------------------------------------------------------------------------*/
    	
    	/*Those five lines is to reverse the motion of the square.
    	 * For Example: 
    	 * when the frameNumber reaches 301, then oscilationFrameNumber1 is 301,
    	 * which is bigger than 300, then 600-301 will be 299, which means the motion will reverse.
    	 * Until the frameNumber reaches 600 which oscilationFrameNumber1 is 0. 
    	 * it will keep looping like this.
    	 * */
    	int oscilationFrameNumber1 = frameNumber % (2*300);
    	if (oscilationFrameNumber1 > 300)
    	{
    		oscilationFrameNumber1 = (2*300) - oscilationFrameNumber1;
    	}
    	
    	/*Cyan square created and ran*/
    	g.setFill(Color.CYAN);
    	g.fillRect(oscilationFrameNumber1, 60, 20, 20);
    	
    	
    	/*Pink square created and ran in a quicker speed.*/
    	int oscilationFrameNumber2 = frameNumber * 3 % (2*300);
    	if (oscilationFrameNumber2 > 300)
    	{
    		oscilationFrameNumber2 = (2*300) - oscilationFrameNumber2;
    	}   	
    	g.setFill(Color.PINK);
    	g.fillRect(oscilationFrameNumber2, 80, 20, 20);
    	
    	
    	/*Yellow square created and ran in a quicker speed.*/
    	int oscilationFrameNumber3 = frameNumber * 5 % (2*300);
    	if (oscilationFrameNumber3 > 300)
    	{
    		oscilationFrameNumber3 = (2*300) - oscilationFrameNumber3;
    	}   	
    	g.setFill(Color.YELLOW);
    	g.fillRect(oscilationFrameNumber3, 100, 20, 20);
    	
    	/*-----------------------------------------------------------------------------------------------------*/
    	
    	
        /* Draw the 5 separate lines
         * CAUTION: Why draw the line to the end of the program? not the start
         * Because: there have a cover relation between them
         * If, first drawing the line, then draw the square, then the extra square part will cover the line.
         * If, first drawing the square, then draw the line, then the line will cover the extra square part.
         * The second schedule will make the screen look more beautiful.
         * */
    	for(int i = 1; i < 6 ; i++)
    	{
    		g.setFill(Color.BLACK);
    		g.strokeLine(0, i * 20, 320, i * 20);
    	}
    	
    }

    //------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------


    public void start(Stage stage) {
        int width = 320;   // The width of the image.  You can modify this value!
        int height = 120;  // The height of the image. You can modify this value!
        Canvas canvas = new Canvas(width,height);
        drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Animation"); // STRING APPEARS IN WINDOW TITLEBAR!
        stage.show();
        stage.setResizable(false);
        AnimationTimer anim = new AnimationTimer() {
            private int frameNum;
            private long startTime = -1;
            private long previousTime;
            public void handle(long now) {
                if (startTime < 0) {
                    startTime = previousTime = now;
                    drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
                }
                else if (now - previousTime > 0.95e9/60) {
                       // The test in the else-if is to make sure that drawFrame() is
                       // called about once every 1/60 second.  It is required since
                       // handle() can be called by the system more often than that.
                    frameNum++;
                    drawFrame(canvas.getGraphicsContext2D(), frameNum, (now-startTime)/1e9, width, height);
                    previousTime = now;
                }
            }
        };
        anim.start();
    } 

    public static void main(String[] args) {
        launch();
    }
    
} // end SimpleAnimationStarter