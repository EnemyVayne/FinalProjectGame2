package Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * The class "Game" is the overarching application, it sets the window up, and
 * creates the panes/scenes/stages. The game starts off by declaring global
 * variables to be used throughout the application.
 *
 * @author Kyle_Faith
 */
public class Game extends Application
{

    public static final int WIDTH = 1300;
    public static final int HEIGHT = 800;
    public Color colorGrass = new Color(0, .47, 0, .8);
    public Color colorRoom = new Color(.5, .2, .10, 1);
    public Color colorHitBox = new Color(1, 1, 1, .5);
    public Color colorBlack = Color.BLACK;

    private Timeline loop2;
    private Timeline loop3;
    private Timeline death;
    private Label titleLabel, titleSpace;
    private boolean boundaryChange = false;
    private boolean enemyCollision = false;
    private boolean playerDead = false;
    private boolean enemyDead = false;


    Stage stage = new Stage();
    Player player = new Player();
    PlayerHealthBar playerHealthBar = new PlayerHealthBar();
    EnemyHealthBar enemyHealthBar = new EnemyHealthBar();
    Enemy enemy = new Enemy();
    Coins coins = new Coins();

    /**
     * The method opens the stage, and creates numerous different objects, which
     * are used to create the panes and scenes. The method starts by first
     * creating the stage and setting its dimensions, then moves on to creating
     * the backgrounds for the panes. The first scene shown is the title screen,
     * which contains two labels. It then requires user input of a space to
     * progress onto the next scene, which is the main game. The new scene has a
     * new player which uses button commands to move. When walking out of the
     * scene, the player moves to the opposite side,as if he was to spawn into a
     * new area.
     *
     * @param stage The first window to be created.
     * @throws Exception
     */
    public void start( Stage stage ) throws Exception
    {

        stage.setResizable(false);
        stage.show();
        stage.setX(300);
        stage.setY(100);
        stage.setMaxHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setMinWidth(WIDTH);

        Rectangle backgroundTitle = new Rectangle(WIDTH, HEIGHT);
        Rectangle backgroundHome = new Rectangle(WIDTH, HEIGHT);
        Rectangle backgroundExplore = new Rectangle(WIDTH, HEIGHT);

        backgroundHome.setFill(colorRoom);
        backgroundExplore.setFill(colorGrass);

        StackPane rootPaneTitle = new StackPane();
        StackPane rootPaneHome = new StackPane();
        StackPane rootPaneExplore = new StackPane();

        VBox titlePane1 = new VBox();
        StackPane titlePane2 = new StackPane();
        HBox coinPane = new HBox();
        coinPane.setAlignment(Pos.TOP_LEFT);

        rootPaneTitle.getChildren().addAll(titlePane2, titlePane1);
        rootPaneHome.getChildren().addAll(backgroundHome, player, enemy,
                playerHealthBar, enemyHealthBar, coinPane);

        Scene TitleScreen = new Scene(rootPaneTitle, WIDTH, HEIGHT);
        Scene HomeScreen = new Scene(rootPaneHome, WIDTH, HEIGHT);
        Scene ExploreScreen = new Scene(rootPaneExplore, WIDTH, HEIGHT);

        titleLabel = new Label("The Suave Squad");
        titleSpace = new Label("Please press Space to continue...");

        String Filepath = "MainMusic.wav";

        Music musicObj = new Music();
        musicObj.playMusic(Filepath);

        titleLabel.setFont(Font.font("", FontWeight.BOLD, 70));
        titleSpace.setFont(Font.font(30));

        titleLabel.setUnderline(true);
        titleLabel.setTextFill(Color.WHITE);
        titleSpace.setTextFill(Color.WHITE);
        titleLabel.setAlignment(Pos.CENTER);
        titleSpace.setAlignment(Pos.CENTER);

        titleSpace.setPadding(new Insets(0, 0, 0, 57.5));
        titlePane1.setPadding(new Insets(HEIGHT / 2 - 100, WIDTH / 2 - 300,
                HEIGHT / 2 - 50, WIDTH / 2 - 300));

        titlePane1.getChildren().addAll(titleLabel, titleSpace);
        titlePane2.getChildren().add(backgroundTitle);
        coinPane.getChildren().addAll(coins.displayCoins(coins.getCoins()));

        stage.setTitle("Kyle And Wills RPG");

        stage.setScene(TitleScreen);

        player.setOnKeyPressed(e
                -> 
                {
                    switch ( e.getCode() )
                    {

                        case UP:
                            player.moveUp();
                            if ( boundaryChange )
                            {
                                rootPaneExplore.getChildren().addAll(
                                        backgroundExplore, player);
                                stage.setScene(ExploreScreen);
                            }
                            break;
                        case DOWN:
                            player.moveDown();
                            if ( boundaryChange )
                            {
                                rootPaneExplore.getChildren().addAll(
                                        backgroundExplore, player);
                                stage.setScene(ExploreScreen);
                                boundaryChange = false;
                            }
                            break;
                        case LEFT:
                            player.moveLeft();
                            if ( boundaryChange )
                            {
                                rootPaneExplore.getChildren().addAll(
                                        backgroundExplore, player);
                                stage.setScene(ExploreScreen);
                                boundaryChange = false;
                            }
                            break;
                        case RIGHT:
                            player.moveRight();
                            if ( boundaryChange )
                            {
                                rootPaneExplore.getChildren().addAll(
                                        backgroundExplore, player);
                                stage.setScene(ExploreScreen);
                                boundaryChange = false;
                            }
                    }
        });

        TitleScreen.setOnKeyPressed(e
                -> 
                {
                    switch ( e.getCode() )
                    {
                        case SPACE:
                            stage.setScene(HomeScreen);
                            break;
                    }
        });

        player.requestFocus();

              EventHandler<ActionEvent> deathHandler = e
              -> 
              {
                 if (enemyDead)
                 {

                    rootPaneHome.getChildren().removeAll(enemy,enemyHealthBar);

                 }

      };
              

              
                    death = new Timeline(
              new KeyFrame(Duration.millis(10), deathHandler));
      death.setCycleCount(Timeline.INDEFINITE);
      death.play();
      

      
        loop2 = new Timeline(
                new KeyFrame(Duration.millis(25), e -> BoundaryCheck()));
        loop2.setCycleCount(Timeline.INDEFINITE);
        loop2.play();

        loop3 = new Timeline(
                new KeyFrame(Duration.millis(30), e -> CollisionDetection()));
        loop3.setCycleCount(Timeline.INDEFINITE);
        loop3.play();

    }

    /**
     * The method checks to see if the players current coordinates are within
     * the boundary set, and if the player moves out of the boundary, it sets
     * the coordinates to the opposite side. (boundaryChange is not used yet,
     * will be used on checkpoint 2 to allow player to truly step into new
     * scenes.)
     */
    public void BoundaryCheck()
    {

        int leftBoundary = 0;
        int rightBoundary = Game.WIDTH;
        int topBoundary = 0;
        int bottomBoundary = Game.HEIGHT - 30;

        if ( player.getX() > rightBoundary ) //boundries for the ball
        {
            player.setX(leftBoundary);
            boundaryChange = true;
        }
        if ( player.getX() < leftBoundary ) //boundries for the ball
        {
            player.setX(rightBoundary);
            boundaryChange = true;
        }
        if ( player.getY() < topBoundary ) //boundries for the ball
        {
            player.setY(bottomBoundary);
            boundaryChange = true;
        }
        if ( player.getY() > bottomBoundary ) //boundries for the ball
        {
            player.setY(topBoundary);
            boundaryChange = true;
        }
    }

    public void CollisionDetection()
    {
        Rectangle playerBounds = player.bounds();
        Rectangle enemyBounds = enemy.bounds();

        Shape intersect = Rectangle.intersect(playerBounds, enemyBounds);

        if ( intersect.getBoundsInLocal().getWidth() != -1 )
        {
            enemyCollision = true;
            
            
            if ( playerHealthBar.getHealth() >= 1 )
            {
                playerHealthBar.LowerHealth();
            } 
            else if ( playerHealthBar.getHealth() == 0 )
            {
                player.Die();
                playerDead = true;
            }
            if ( enemyHealthBar.getHealth() > 0 )
            {

                 enemyHealthBar.LowerHealth();
            } 
            else if ( enemyHealthBar.getHealth() == 0 )
            {
                int coin = 0;
                enemyDead = true;
                
                while(coin<1)
                {
                    Coins.setCoins(( int ) ((Math.random() * ((10 - 1) + 1)) + 1));
                    coins.displayCoins(coins.getCoins());
                    System.out.println(coins.getCoins());
                  coin++;
                }

            }

        } else
        {
            enemyCollision = false;
            playerHealthBar.RaiseHealth();
        }

        if ( enemyCollision )
        {
            enemy.pauseEnemy();
        } else if ( !enemyCollision )
        {
            enemy.playEnemy();
        }

    }

    public static void main( String[] args )
    {
        Application.launch(args);
    }

}
