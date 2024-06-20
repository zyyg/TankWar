package com.zyy;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * @Author yyzhou
 * @Date 2024/6/19 17:02
 * @PackageName:com.zyy
 * @ClassName: Bullet
 * @Description: 子弹类
 * @Version 1.0
 */
public class Bullet extends GameObject{
    //大小
    int width=10;
    int height=10;
    //速度
    int speed=7;
    //方向
    Direction direction;

    public Bullet(String img, int x, int y, GamePanel gamePanel,Direction direction) {
        super(img, x, y, gamePanel);
        this.direction = direction;
    }

    public void leftWard(){
        x-=speed;
    }

    public void rightWard(){
        x+=speed;
    }

    public void upWard(){
        y-=speed;
    }

    public void downWard(){
        y+=speed;
    }
    public void go(){
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
        this.hitWall();
        this.moveToBorder();
        this.hitBase();
    }

    /**
     * 移除出界子弹
     */
    public void moveToBorder(){
        if(x<0||x+width>this.gamePanel.getWidth()){
            this.gamePanel.removeList.add(this);
        }
        if (y<0||y+height>this.gamePanel.getHeight()){
            this.gamePanel.removeList.add(this);
        }
    }

    /**
     * 子弹击中墙壁
     */
    public void hitWall(){
        ArrayList<Wall> walls=this.gamePanel.wallList;
        for(Wall wall:walls){
            if(this.getRec().intersects(wall.getRec())){
                this.gamePanel.wallList.remove(wall);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    /**
     * 基地碰撞检测
     */
    public void hitBase(){
        ArrayList<Base> baseList=this.gamePanel.baseList;
        for (Base base:baseList) {
            if(this.getRec().intersects(base.getRec())){
                this.gamePanel.baseList.remove(base);
                this.gamePanel.removeList.add(this);
                break;
            }

        }
    }

    /**
     * 玩家击中敌方坦克
     */
    public void hitBot(){
        ArrayList<Bot> bots=this.gamePanel.botList;
        for (Bot bot:bots) {
            if(this.getRec().intersects(bot.getRec())){
                this.gamePanel.blastList.add(new Blast("",bot.x-34,bot.y-14,this.gamePanel));
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }

        }
    }
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
        this.hitBot();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
