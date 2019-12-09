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
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * The class "Game" is the overarching application, it sets the window up, and
 * creates the panes/scenes/stages. The game starts off by declaring global
 * variables to be used throughout the application.
 *
 * @author Kyle Faith
 */
public class Game extends Application
{

    public static final int WIDTH = 1300;
    public static final int HEIGHT = 800;
    public Color colorGrass = new Color(0, .47, 0, .8);
    public Color colorRoom = new Color(.5, .2, .10, 1);
    public Color colorBlack = Color.BLACK;

    private Timeline loop2;
    private Timeline loop3;
    private Timeline handler;
    private Timeline boldChecker;

    private Label titleLabel, titleSpace, itemLabel1, itemLabel2, itemLabel3, itemLabel4, itemLabel5;
    private Button btnBuy1, btnBuy2, btnBuy3, btnBuy4, btnBuy5, btnClose;
    private boolean boundaryChange = false;
    private boolean enemyCollision = false;
    private boolean playerRecall = false;
    private boolean enemyDead = false;
    private boolean npcIntersect = false;
    private boolean npcHere = false;
    private boolean shopPaneOpen = false;

    Player player = new Player();
    PlayerHealthBar playerHealthBar = new PlayerHealthBar();
    EnemyHealthBar enemyHealthBar = new EnemyHealthBar();
    Enemy enemy = new Enemy();
    Coins coins = new Coins();
    NPC npc = new NPC();

    HBox HUDPane = new HBox();

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
     * 
     * @author Kyle Faith
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

        backgroundHome.setFill(colorRoom);

        StackPane rootPaneTitle = new StackPane();
        StackPane rootPaneHome = new StackPane();

        VBox titlePane1 = new VBox();
        StackPane titlePane2 = new StackPane();

        HUDPane.setAlignment(Pos.TOP_LEFT);

        rootPaneTitle.getChildren().addAll(titlePane2, titlePane1);
        rootPaneHome.getChildren().addAll(backgroundHome, player, enemy,
                playerHealthBar, enemyHealthBar, HUDPane);

        Scene TitleScreen = new Scene(rootPaneTitle, WIDTH, HEIGHT);
        Scene HomeScreen = new Scene(rootPaneHome, WIDTH, HEIGHT);

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
        HUDPane.getChildren().addAll(coins.displayCoins(coins.getCoins()));

        stage.setTitle("Kyle And Wills RPG");

        stage.setScene(TitleScreen);

        player.setOnKeyPressed(e
                -> 
                {
                    switch ( e.getCode() )
                    {
                        case W:
                            player.moveUp();

                            break;
                        case S:
                            player.moveDown();
                            break;
                        case A:
                            player.moveLeft();

                            break;
                        case D:
                            player.moveRight();
                            break;
                        case B:
                            npcHere = true;
                            playerRecall = true;
                            break;
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

        EventHandler<ActionEvent> Handler = e
                -> 
                {
                    if ( enemyDead )
                    {
                        rootPaneHome.getChildren().removeAll(enemy,
                                enemyHealthBar);
                    }
                    if ( boundaryChange )
                    {
                        backgroundHome.setFill(colorGrass);
                        rootPaneHome.getChildren().removeAll(enemy,
                                enemyHealthBar, npc);
                        rootPaneHome.getChildren().addAll(enemy,
                                enemyHealthBar);
                        RespawnEnemy();

                        boundaryChange = false;
                    }
                    if ( playerRecall )
                    {
                        rootPaneHome.getChildren().removeAll(enemy,
                                enemyHealthBar, enemy.bounds());
                        player.Spawn();
                        backgroundHome.setFill(colorRoom);
                        rootPaneHome.getChildren().addAll(npc);
                        playerRecall = false;
                    }
                    if ( npcIntersect )
                    {
                        if ( rootPaneHome.getChildren().size() <= 5 )
                        {
                            rootPaneHome.getChildren().addAll(ShopPane());
                            shopPaneOpen = true;
                        }
                    }
                    if ( !npcIntersect )
                    {
                        if ( rootPaneHome.getChildren().size() == 6 
                                && shopPaneOpen == true )
                        {
                            rootPaneHome.getChildren().remove(5);
                            shopPaneOpen = false;
                        }
                    }
        };

        handler = new Timeline(
                new KeyFrame(Duration.millis(30), Handler));
        handler.setCycleCount(Timeline.INDEFINITE);
        handler.play();

        loop2 = new Timeline(
                new KeyFrame(Duration.millis(30), e -> BoundaryCheck()));
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
     * 
     * @author Kyle Faith
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

    /**
     * This method checks to see if the players current coordinates are
     * coinciding with either the Enemy or the NPC. If the player is coinciding
     * with the enemy, it calls functions which lower both the enemy and the
     * players health bars.If the player is coinciding with the NPC, the shop
     * pane opens up allowing the player to buy items to either do more damage
     * or to move faster.
     * 
     * @author Kyle Faith
     */
    public void CollisionDetection()
    {

        Rectangle playerBounds = player.bounds();

        if ( enemyDead == false )
        {
            Rectangle enemyBounds = enemy.bounds();
            Shape intersectEnemy = Rectangle.intersect(playerBounds, enemyBounds);
            if ( intersectEnemy.getBoundsInLocal().getWidth() != -1 )
            {
                enemyCollision = true;

                if ( playerHealthBar.getHealth() >= 1 )
                {
                    playerHealthBar.LowerHealth();
                } 
                else if ( playerHealthBar.getHealth() == 0 )
                {
                    player.Die();
                }
                if ( enemyHealthBar.getHealth() > 0 )
                {
                    enemyHealthBar.LowerHealth();
                } 
                else if ( enemyHealthBar.getHealth() <= 0 )
                {
                    enemyDead = true;
                    coins.Calc();
                    coins.displayCoins(coins.getCoins());
                    HUDPane.getChildren().clear();
                    HUDPane.getChildren().addAll(coins.displayCoins(
                            coins.getCoins()));

                }

            } 
            else
            {
                enemyCollision = false;
                playerHealthBar.RaiseHealth();
            }
        }

        if ( enemyCollision )
        {
            enemy.pauseEnemy();
        } 
        else if ( !enemyCollision )
        {
            enemy.playEnemy();
        }
        if ( npcHere )
        {
            Rectangle npcBounds = npc.bounds();
            Shape intersectNPC = Rectangle.intersect(playerBounds, npcBounds);
            if ( intersectNPC.getBoundsInLocal().getWidth() != -1 )
            {

                npcIntersect = true;

            } 
            else
            {

                npcIntersect = false;
            }
        }
    }

    /**
     * This method sets the boolean enemyDead to false to allow for collision
     * detection again, and spawns the enemy in a random place, and spawns a
     * health bar of a random size.
     * 
     * @author Kyle Faith
     */
    public void RespawnEnemy()
    {
        enemyDead = false;
        enemyHealthBar.Spawn();
        enemy.Spawn();
    }

    /**
     * This method creates the entire shop, which is only opened when the player
     * object coincides with the NPC. It sets up buttons for buying objects, and
     * labels for descriptions on what is being bought. The final button is a
     * close button which closes the shop pane out, and allows the player to
     * move again.
     *
     * @return rootPaneShop, which is a pane that added to the scene when a
     * player walks into the NPC.
     * 
     * @author Kyle Faith
     */
    public StackPane ShopPane()
    {
        StackPane rootPaneShop = new StackPane();
        GridPane Pane = new GridPane();
        Pane.setHgap(5);
        Pane.setVgap(5);

        Pane.setAlignment(Pos.CENTER);

        Rectangle backgroundRectangle = new Rectangle(WIDTH / 2, HEIGHT / 2,
                WIDTH / 2, HEIGHT / 2);
        backgroundRectangle.setStroke(Color.BLACK);
        backgroundRectangle.setFill(Color.ANTIQUEWHITE);

        itemLabel1 = new Label("Stick : 10 gold");
        itemLabel2 = new Label("Iron Sword : 25 gold");
        itemLabel3 = new Label("Steel Sword : 50 gold");
        itemLabel4 = new Label("Diamond Sword : 100 gold");
        itemLabel5 = new Label("Boots : 30 gold");

        EventHandler<ActionEvent> eventHandler = e
                -> 
                {
                    if ( coins.getCoins() >= 10 )
                    {
                        itemLabel1.setFont(Font.font("", FontWeight.BOLD, 25));
                    } 
                    else
                    {
                        itemLabel1.setFont(Font.font(""));
                    }
                    if ( coins.getCoins() >= 25 )
                    {
                        itemLabel2.setFont(Font.font("", FontWeight.BOLD, 25));
                    } 
                    else
                    {
                        itemLabel1.setFont(Font.font(""));
                    }
                    if ( coins.getCoins() >= 50 )
                    {
                        itemLabel3.setFont(Font.font("", FontWeight.BOLD, 25));
                    }
                    else
                    {
                        itemLabel1.setFont(Font.font(""));

                    }
                    if ( coins.getCoins() >= 100 )
                    {
                        itemLabel4.setFont(Font.font("", FontWeight.BOLD, 25));
                    } 
                    else
                    {
                        itemLabel1.setFont(Font.font(""));

                    }
                    if ( coins.getCoins() >= 30 )
                    {
                        itemLabel5.setFont(Font.font("", FontWeight.BOLD, 25));
                    } 
                    else
                    {
                        itemLabel1.setFont(Font.font(""));

                    }

        };

        boldChecker = new Timeline(
                new KeyFrame(Duration.millis(100), eventHandler));
        boldChecker.setCycleCount(Timeline.INDEFINITE);
        boldChecker.play();

        btnBuy1 = new Button("Buy");
        btnBuy2 = new Button("Buy");
        btnBuy3 = new Button("Buy");
        btnBuy4 = new Button("Buy");
        btnBuy5 = new Button("Buy");
        btnClose = new Button("Close");

        Pane.add(itemLabel1, 0, 0);
        Pane.add(btnBuy1, 1, 0);
        Pane.add(itemLabel2, 0, 1);
        Pane.add(btnBuy2, 1, 1);
        Pane.add(itemLabel3, 0, 2);
        Pane.add(btnBuy3, 1, 2);
        Pane.add(itemLabel4, 0, 3);
        Pane.add(btnBuy4, 1, 3);
        Pane.add(itemLabel5, 0, 4);
        Pane.add(btnBuy5, 1, 4);
        Pane.add(btnClose, 1, 5);

        if ( coins.getCoins() >= 10 )
        {
            btnBuy1.setOnAction(( ActionEvent e )
                    -> 
                    {

                        enemyHealthBar.setSpeedX(2);
                        Pane.add(new Label("BOUGHT"), 2, 0);
                        coins.setCoins(-10);
                        HUDPane.getChildren().clear();
                        HUDPane.getChildren().addAll(coins.displayCoins(
                                coins.getCoins()));
            }
            );
        }
        if ( coins.getCoins() >= 25 )
        {
            btnBuy2.setOnAction(( ActionEvent e )
                    -> 
                    {
                        enemyHealthBar.setSpeedX(3);
                        Pane.add(new Label("BOUGHT"), 2, 1);

                        coins.setCoins(-25);
                        HUDPane.getChildren().clear();
                        HUDPane.getChildren().addAll(coins.displayCoins(
                                coins.getCoins()));
            }
            );
        }
        if ( coins.getCoins() >= 50 )
        {
            btnBuy3.setOnAction(( ActionEvent e )
                    -> 
                    {
                        enemyHealthBar.setSpeedX(4);
                        Pane.add(new Label("BOUGHT"), 2, 2);

                        coins.setCoins(-50);
                        HUDPane.getChildren().clear();
                        HUDPane.getChildren().addAll(coins.displayCoins(
                                coins.getCoins()));
            }
            );
        }
        if ( coins.getCoins() >= 100 )
        {
            btnBuy4.setOnAction(( ActionEvent e )
                    -> 
                    {
                        enemyHealthBar.setSpeedX(5);
                        Pane.add(new Label("BOUGHT"), 2, 3);

                        coins.setCoins(-100);
                        HUDPane.getChildren().clear();
                        HUDPane.getChildren().addAll(coins.displayCoins(
                                coins.getCoins()));
            }
            );
        }
        if ( coins.getCoins() >= 30 )
        {
            btnBuy5.setOnAction(( ActionEvent e )
                    -> 
                    {

                        Pane.add(new Label("BOUGHT"), 2, 4);
                        player.setSpeedX(15);
                        player.setSpeedY(15);
                        coins.setCoins(-30);
                        HUDPane.getChildren().clear();
                        HUDPane.getChildren().addAll(coins.displayCoins(
                                coins.getCoins()));
            }
            );

        }
        btnClose.setOnAction(( ActionEvent e )
                -> 
                {
                    rootPaneShop.getChildren().removeAll(backgroundRectangle,
                            Pane);
                    player.requestFocus();
        }
        );

        rootPaneShop.getChildren().addAll(backgroundRectangle, Pane);
        return rootPaneShop;
    }

    public static void main( String[] args )
    {
        Application.launch(args);
    }

}
