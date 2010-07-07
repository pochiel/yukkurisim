
package yukkurisim;

import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.AnimatedSprite;

import yukkurisim.yukkuri_base;
import yukkurisim.Mapchip_base;
import yukkurisim.MapObject_Manager;

// 定数クラス
import gamestatus.Const_Value;

// シーンクラス
import game_scene.*;

// タイマクラス
import gamestatus.GameTimer;
import java.io.*;
public class yukkurisim_main extends Game implements Serializable {	
    public Background background; 							// 背景
    public UniqueIdManager IdMan = new UniqueIdManager();	// オブジェクトIDのマネージャ
    private ImageLoader iLoader;							// イメージローダ参照
    private int SceneNo;									// シーンの管理番号。

    TitleScene				title;
    Game01					gamescene;						//ニューゲームのシーン
    LoadGame				loadscene;						//ゲームのロードシーン
    /**
     * 初期化
     */
    public Physical_Law_Facade phy_law;
    static  Const_Value	定数;

    public void initResources() {
    	background = new ColorBackground(Color.black, 定数.画面幅, 定数.画面高さ);
    	phy_law = Physical_Law_Facade.Get_Instance(this);
    	
    	/*********** シーンの宣言 ここから **************/
    	title 	= new TitleScene(this);				//タイトル画面
    	
    	//シーンの初期化
    	title.initResources();

    	/*********** シーンの宣言 ここまで **************/
    	SceneNo = 定数.SCENE_TITLE;		//タイトルシーンからスタート
    }

    /**
     * 更新
     */
    public void update(long elapsedTime) {
    	
    	//backgroundのレンダリングはシーン側でやるべき	    	
        if(SceneNo == 定数.SCENE_TITLE)
        {
        	title.update(elapsedTime);
        }
        if( SceneNo == 定数.SCENE_GAME01)
        {
        	gamescene.update(elapsedTime);
        }
        if( SceneNo == 定数.SCENE_LOAD )
        {
        	loadscene.update(elapsedTime);
        }
        	
        // ESCで終了        
        if (keyPressed(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
    }

    /**
     * レンダリング
     */
    public void render(Graphics2D g) {

    	if(SceneNo == 定数.SCENE_TITLE)
        {
    		title.render(g);
        }
        if(SceneNo == 定数.SCENE_GAME01)
        {
        	gamescene.render(g);
        }
        if(SceneNo == 定数.SCENE_LOAD)
        {
        	loadscene.render(g);
        }
    }

    public static void main(String[] args) {
        定数 = new Const_Value();
    	GameLoader 	game = new GameLoader();
        // 1024x768のフルスクリーンモード
        game.setup(new yukkurisim_main(), new Dimension(定数.画面幅, 定数.画面高さ), false);
        game.start();
    }
    
    /*
    public void GotoNextScene()
    {
    	SceneNo++;
    }
    public void BacktoNextScene()
    {
    	SceneNo--;
    }*/
    public void JumpSceneto(int no)
    {
    	if( (no==定数.SCENE_GAME01)&&(gamescene==null) )
    	{
        	gamescene = new Game01(this);					//ニューゲームのシーン
        	gamescene.initResources();
    	}
    	if( (no==定数.SCENE_LOAD)&&(loadscene==null) )
    	{
        	loadscene = new LoadGame(this);				//ゲームのロードシーン
        	loadscene.initResources();
    	}
    	SceneNo = no;
    }

    /**
     * ゲームのメインシーンが現在フェード処理中かを返す
     * @return
     */
	public boolean GameIsFading()
	{
    	if(SceneNo == 定数.SCENE_TITLE)
        {
    		return title.GameIsFading();
        }
        if(SceneNo == 定数.SCENE_GAME01)
        {
        	return gamescene.GameIsFading();
        }
        if(SceneNo == 定数.SCENE_LOAD)
        {
        	return loadscene.GameIsFading();
        }
        return false;

	}

}
