package com.zyy;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @Author yyzhou
 * @Date 2024/6/19 22:40
 * @PackageName:com.zyy
 * @ClassName: EnemyBullet
 * @Description: TODO
 * @Version 1.0
 */
public class EnemyBullet extends Bullet{
    public EnemyBullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel, direction);
    }

    public void hitPlayer(){
        ArrayList<Tank> players=this.gamePanel.playerList;
        for (Tank player:players) {
            if(this.getRec().intersects(player.getRec())){
                this.gamePanel.blastList.add(new Blast("",player.x-34,player.y-14,this.gamePanel));
                this.gamePanel.playerList.remove(player);
                this.gamePanel.removeList.add(this);
                player.alive=false;
                break;
            }

        }
    }

    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
        hitPlayer();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }

}
