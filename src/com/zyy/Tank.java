package com.zyy;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * @Author yyzhou
 * @Date 2024/6/19 14:52
 * @PackageName:com.zyy
 * @ClassName: Tank
 * @Description: TODO
 * @Version 1.0
 */
public abstract class Tank extends GameObject{
    //大小
    public int width=40;
    public int height=50;
    public boolean left=false;
    public boolean right=false;
    public boolean up=false;
    public boolean down=false;

    //生命
    public boolean alive=false;
    //方向
    public Direction direction=Direction.UP;

    //速度
    private int speed=3;

    //四个方向图片
    private String upImg;
    private String leftImg;
    private String rightImg;
    private String downImg;

    //攻击冷却状态
    private boolean attackCoolDown=true;
    //攻击冷却时间毫秒级间隔1000ms
    private int attackCoolDownTime=100;

    public Tank(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel);
        this.upImg = upImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.downImg = downImg;
    }

    /**
     * 向左移动
     */
    public void leftWard(){
        direction=Direction.LEFT;
        setImg(leftImg);
        if(!hitWall(x-speed,y) && !moveToBorder(x-speed,y)){
            this.x-=speed;
        }

    }
    /**
     * 向上移动
     */
    public void upWard(){
        direction=Direction.UP;
        setImg(upImg);
        if(!hitWall(x,y-speed) && !moveToBorder(x,y-speed)){
            this.y-=speed;
        }
    }
    /**
     * 向右移动
     */
    public void rightWard(){
        direction=Direction.RIGHT;
        setImg(rightImg);
        if(!hitWall(x+speed,y) && !moveToBorder(x+speed,y)){
            this.x+=speed;
        }
    }
    /**
     * 向下移动
     */
    public void downWard(){
        direction=Direction.DOWN;
        setImg(downImg);
        if(!hitWall(x,y+speed) && !moveToBorder(x,y+speed)){
            this.y+=speed;
        }
    }

    //新线程
    class AttackCD extends Thread{
        public void run(){
            //将攻击功能设置为冷却状态
            attackCoolDown=false;
            try {
                Thread.sleep(attackCoolDownTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            attackCoolDown=true;
            this.stop();
        }
    }


    public void setImg(String img){
        this.img=Toolkit.getDefaultToolkit().getImage(img);
    }

    @Override
    public abstract void paintSelf(Graphics g);

    @Override
    public abstract Rectangle getRec();

    public void attack(){
        if(attackCoolDown&&alive){
            Point p=this.getHeadPoint();
            Bullet bullet = new Bullet("images/bullet/bulletGreen.gif", p.x, p.y, this.gamePanel, direction);
            this.gamePanel.bulletList.add(bullet);
            new AttackCD().start();
        }
    }
    public Boolean hitWall(int x, int y){
        ArrayList<Wall> walls=this.gamePanel.wallList;
        Rectangle next=new Rectangle(x,y,width,height);
        for(Wall wall:walls){
            if(next.intersects(wall.getRec())){
                return true;
            }
        }
        return false;
    }

    public Boolean moveToBorder(int x,int y){
        if(x<0){
            return true;
        }
        else if(x+width>this.gamePanel.getWidth()){
            return true;
        }
        else if(y<0){
            return true;
        }
        else if(y+height>this.gamePanel.getHeight()){
            return false;
        }
        return false;
    }
    public Point getHeadPoint(){
        switch (direction){
            case LEFT:
                return new Point(x,y+height/2);
            case RIGHT:
                return new Point(x+width,y+height/2);
            case UP:
                return new Point(x+width/2,y);
            case DOWN:
                return new Point(x+width/2,y+height);
            default:
                return null;
        }
    }
}
