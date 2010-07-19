
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
    Loading					nowloading;						//ゲームのローディングシーン
    
    /**
     * 初期化
     */
    public Physical_Law_Facade phy_law;
    static  Const_Value	定数;

    public void initResources() {
    	background = new ColorBackground(Color.black, 定数.画面幅, 定数.画面高さ);
    	phy_law = Physical_Law_Facade.Get_Instance(this);
    	
    	/*********** シーンの宣言 ここから **************/
    	title 	= new TitleScene(this);				// タイトル画面
    	nowloading = new Loading(this);				// ローディング

    	//シーンの初期化
    	title.initResources();
    	nowloading.initResources();
    	
    	/*********** シーンの宣言 ここまで **************/
    	SceneNo = 定数.SCENE_TITLE;		//タイトルシーンからスタート
    	
    	mouseUPFlag = false;
    	lastMouseState = false;
    }

    /**
     * 更新
     */
    public void update(long elapsedTime) {
    	updateMouseUPFlag();	// MouseUPイベント監視
    	
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
        if( SceneNo == 定数.SCENE_LOADING)
        {
        	nowloading.update(elapsedTime);
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
        if(SceneNo == 定数.SCENE_LOADING)
        {
        	nowloading.render(g);
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
    	SceneNo = no;
    	
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
        if(SceneNo == 定数.SCENE_LOADING)
        {
        	return nowloading.GameIsFading();
        }
        
        return false;
	}

	/** ローディングクラスから受け渡されるイメージローダクラスへのインスタンス受け取り窓口 **/
	public void setImageLoader( ImageLoader i )
	{
		iLoader = i;
	}
	
	public ImageLoader getImageLoader()
	{
		return iLoader;
	}
	
	
	/**
	 * ローディングシーンに入る前に、ロード終了後のシーンを通知しておく
	 * @param sceneno
	 */
	public void setLoadEndedScene( int sceneno )
	{
		nowloading.setNextSceneNo(sceneno);
	}
	
	private boolean mouseUPFlag;		// MouseUPの瞬間にtrueとなる
	private boolean lastMouseState;	// １ループ前のマウスの状態を保持する

	private void updateMouseUPFlag()
	{
		if( (!this.bsInput.isMouseDown(1)) && lastMouseState)	// 前回状態はオンで、現在状態はオフだ
		{	// 離された瞬間
			mouseUPFlag = true;
		}
		else
		{
			mouseUPFlag = false;
		}
		lastMouseState = this.bsInput.isMouseDown(1);	// 次ループ用に保持
	}
	
	/**
	 * マウスが離された瞬間にtrueとなる。
	 * @return
	 */
	public boolean isMouseUp()
	{
		return mouseUPFlag;
	}
}
