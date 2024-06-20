package com.zyy;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Author yyzhou
 * @Date 2024/6/20 11:43
 * @PackageName:com.zyy
 * @ClassName: PlayerTwo
 * @Description: TODO
 * @Version 1.0
 */
public class PlayerTwo extends Tank{

    public PlayerTwo(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,height,width);
    }

    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
        System.out.println(e.getKeyCode());
        switch (key){
            case KeyEvent.VK_LEFT:
                left=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
            case KeyEvent.VK_UP:
                up=true;
            case KeyEvent.VK_SPACE:
                attack();
            default:
                break;

        }
    }

    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            case KeyEvent.VK_UP:
                up=false;
                break;

        }
    }

    public void move(){
        if(left){
            leftWard();
        }
        else if(right){
            rightWard();
        }else if(up){
            upWard();
        }else if(down) {
            downWard();
        }
    }
}

