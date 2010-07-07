package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Comparator;

import yukkurisim.*;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import yukkurisim.MapObject_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.Mapchip_base;
import yukkurisim.yukkuri_base;
import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.Widget_Base;
import yukkurisim.Widget_Manager;
import yukkurisim.MapTileManager;
import yukkurisim.Physical_Law_Facade;

/*********************************************************
 * 
 * @author ポチエル
 *
 * ゲームシーン１.
 *********************************************************/

public class Game01 extends Scene_Base  implements Serializable {
    MapObject_Manager 	OnMapObj;					// 霊夢を含めた全てのオブジェクト
    												// エリア上の木等のオブジェクトも含まれる
    												// これらを分けてグルーピングできないのは
    												// レンダリングの関係である。

    ItemManager			itemMan;					// アイテムマネージャ
    
    ADV_SpriteGroup_base	map1;					// マップ
    Widget_Manager			window;					// ウィンドウグループ
    
    ////////////////////////////////////////////////////////
    private int			Message_State = 1;		// メッセージ表示用カウンタ
    private MapTileManager	MapMan;					// マップマネージャ
    //private MapObject_Manager Obj_Info;
    private Cursor_Manager	OwnCursor;				// カーソル
    private ImageLoader iLoader;					// イメージローダ
    ////////////////////////////////////////////////////////
    
    private Background 	background; // 背景
    
    ////////////////////////////////////////////////////////
   
	
	/*******************************************************
	 * <p>コンストラクタ</p>
	 * @param yukkuri
	 * @param map
	 * @param own
	 */
	/*public TestField( yukkurisim_main own , ADV_SpriteGroup_base yukkuri , ADV_SpriteGroup_base map )
	{
		super(own);
		OnMapObj = yukkuri;
		map1 = map;
	}*/
    public Game01( yukkurisim_main own )
    {
    	super(own);
    }
    public void initResources() {
    	super.initResources();
    	this.alpha = 0.0f;

    	this.background = new ColorBackground(Color.black, 定数.画面幅, 定数.画面高さ);
    	
    	iLoader = ImageLoader.Get_Instance(owner);

    	OnMapObj = new MapObject_Manager(owner,"YUKKURI_REIMU");
    	
    	/********** マップの宣言 ***********************/
    	int[][] mapgrid = {
    						/*{1,1,1,1,1,1,4,3,1,1},
    						{1,1,1,1,1,1,4,3,1,1},
    						{1,1,1,1,1,1,1,4,3,1},
    						{1,1,1,1,1,1,1,4,3,1},
    						{3,1,1,1,1,1,1,1,4,3},
    						{3,1,1,1,1,1,1,1,4,3},
    						{4,3,1,1,1,1,1,1,1,4},
    						{4,3,1,1,1,1,1,1,1,4},
    						{1,4,3,1,1,1,1,1,1,1},
    						{1,4,3,1,1,1,1,1,1,1},
    						{1,1,4,3,1,1,1,1,1,1},
    						{1,1,4,3,1,1,1,1,1,1},
    						{1,1,1,4,3,1,1,1,1,1},
    						{1,1,1,4,3,1,1,1,1,1},
    						{1,1,1,1,4,3,1,1,1,1},
    						{1,1,1,1,4,3,1,1,1,1},
    						{1,1,1,1,1,4,3,1,1,1},
    						{1,1,1,1,1,4,3,1,1,1},
    						{1,1,1,1,1,1,4,3,1,1},
    						{1,1,1,1,1,1,4,3,1,1},
    						{1,1,1,1,1,1,1,4,3,1},
    						{1,1,1,1,1,1,1,4,3,1},
    						{1,1,1,1,1,1,1,1,4,3},
    						{1,1,1,1,1,1,1,1,4,3},
    						{1,1,1,1,1,1,1,1,1,4},*/
    			{0,0,0,0,0,0,1,0,0,0,0,0,0},
    			{0,0,0,0,0,1,1,0,0,0,0,0,0},
    			{0,0,0,0,0,1,1,1,0,0,0,0,0},
    			{0,0,0,0,1,1,1,1,0,0,0,0,0},
    			{0,0,0,0,1,1,1,1,1,0,0,0,0},
    			{0,0,0,1,1,1,1,1,1,0,0,0,0},
    			{0,0,0,1,1,1,1,1,1,1,0,0,0},
    			{0,0,3,1,1,1,1,1,1,1,0,0,0},
    			{0,0,3,1,1,1,1,1,1,1,1,0,0},
    			{0,1,4,3,1,1,1,1,1,1,1,0,0},
    			{0,1,4,3,1,1,1,1,1,1,1,1,0},
    			{1,1,1,4,3,1,1,1,1,1,1,1,0},
    			{1,1,1,4,3,1,1,1,1,1,1,1,1},
    			{1,1,1,1,4,3,1,1,1,1,1,1,1},
    			{0,1,1,1,4,3,1,1,1,1,1,1,1},
    			{0,1,1,1,1,1,1,1,1,1,1,1,0},
    			{0,0,1,1,1,1,1,1,1,1,1,1,0},
    			{0,0,1,1,1,1,1,1,1,1,1,0,0},
    			{0,0,0,1,1,1,1,1,1,1,1,0,0},
    			{0,0,0,1,1,1,1,1,1,1,0,0,0},
    			{0,0,0,0,1,1,1,1,1,1,0,0,0},
    			{0,0,0,0,1,1,1,1,1,0,0,0,0},
    			{0,0,0,0,0,1,1,1,1,0,0,0,0},
    			{0,0,0,0,0,1,1,1,0,0,0,0,0},
    			{0,0,0,0,0,0,1,1,0,0,0,0,0},
    			{0,0,0,0,0,0,1,0,0,0,0,0,0},

    	};
  
    	// maptilemanagerとmapobjectmanagerを作成し、物理法則クラスへ登録する
    	MapMan = new MapTileManager(owner , mapgrid);		//マップマネージャでマップを作成する。
    	map1 = MapMan.GetMapGroup();						//マップマネージャでマップを作成する。
    	
    	// アイテムマネージャクラスの登録
    	this.itemMan = ItemManager.Get_Instance(owner);
    	
    	// 物理法則クラスへの登録
    	owner.phy_law.Set_Manager(MapMan, OnMapObj,itemMan);

    	// ゆっくり本体の生成
    	for ( int i= 0 ; i< 30; i++)
    	{
    		yukkuri_base	tmp = new yukkuri_base( owner );
    		tmp.location(5, 6);
    		OnMapObj.add( tmp ,tmp.Get_Type());    		
    	}
    	
    	//OnMapObj.Initialize();	//全オブジェクトの初期化
    	
    	/*********** Comparatorをセット ここから **************/
    	/*********** マップ上のソート順序の指定はこちら *******/
    	OnMapObj.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(( s1.getY() + s1.getHeight() ) - ( s2.getY() + s2.getHeight()) );
    				}
    			}
    	);
    	/*********** Comparatorをセット ここまで **************/
    	
    	/*********** Backgroundをセット ここから **************/

    	OnMapObj.setBackground(background);
    	map1.setBackground(background);
    	
    	/*********** Backgroundをセット ここまで **************/
    	
       	//windowグループを取得
       	window = Widget_Manager.Get_Instance(owner).GetWindowGroup();
       	/*********** widget をセット ここまで **************/

       	OwnCursor = Cursor_Manager.Get_Instance(owner);		// カーソルスクロール
       	
       	this.FadeInScreen();
       	
    }
    
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		
		background.update(elapsedTime);
		map1.update(elapsedTime);
		if(window.GTimer.isMoving())
		{
			OnMapObj.update(elapsedTime);
		}
		OnMapObj.doClickEvent();	// クリックイベントはタイマの状態に強制されるべきではない
		
		//メッセージウインドウ系とか
		window.update(elapsedTime);
		
		OwnCursor.update(elapsedTime);			// カーソルスクロール
		
		owner.phy_law.checkCollision();			// 衝突検出
	}
	
	public void render(Graphics2D g)
	{	
		super.render(g);
		
		//background.render(g);	//バックグラウンドの描画
		map1.render(g);			//マップの描画
		OwnCursor.render(g);	//カーソルの描画
		OnMapObj.render(g);		//マップ上のオブジェクトの描画
		window.render(g);		//メニュー等、ウインドウの描画
	}
}
