package com.zyy;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.Random;

/**
 * @Author yyzhou
 * @Date 2024/6/19 22:15
 * @PackageName:com.zyy
 * @ClassName: Bot
 * @Description: TODO
 * @Version 1.0
 */
public class Bot extends Tank{

    int moveTime=0;
    public Bot(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }

    public Direction getRandomDirection(){
        Random random=new Random();
        int xrun=random.nextInt(4);
        switch (xrun){
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return null;
        }
    }

    public void go(){
        attack();
        if(moveTime>20){
            direction=getRandomDirection();
            moveTime=0;
        }else {
            moveTime++;
        }
        switch (direction){
            case LEFT:
                leftWard();
                break;
            case RIGHT:
                rightWard();
                break;
            case UP:
                upWard();
                break;
            case DOWN:
                downWard();
                break;
        }
    }
    public void attack(){
        Point p=getHeadPoint();
        Random random=new Random();
        int rnum=random.nextInt(100);
        if(rnum<4){
            this.gamePanel.bulletList.add(new EnemyBullet("images/bullet/bulletYellow.gif",p.x,p.y,this.gamePanel,direction));
        }
    }
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }

}
