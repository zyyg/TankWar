package com.zyy;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Author yyzhou
 * @Date 2024/6/19 15:03
 * @PackageName:com.zyy
 * @ClassName: PlayerOne
 * @Description: TODO
 * @Version 1.0
 */
public class PlayerOne extends Tank{

    public PlayerOne(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
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
            case KeyEvent.VK_A:
                left=true;
                break;
            case KeyEvent.VK_S:
                down=true;
                break;
            case KeyEvent.VK_D:
                right=true;
                break;
            case KeyEvent.VK_W:
                up=true;
                break;
            case KeyEvent.VK_SPACE:
                attack();
            default:
                break;

        }
    }

    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_A:
                left=false;
                break;
            case KeyEvent.VK_S:
                down=false;
                break;
            case KeyEvent.VK_D:
                right=false;
                break;
            case KeyEvent.VK_W:
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
