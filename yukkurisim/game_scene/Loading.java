package game_scene;

import java.awt.Graphics2D;

import yukkurisim.ImageLoader;
import yukkurisim.yukkurisim_main;

public class Loading extends Scene_Base {

	/*
	 * ヒープ上のデータにアクセスする際の、もう1つの注意点は
	 * スレッドからヒープへのアクセス時には、キャッシュが利用されるという点です。
	 * 全てのスレッドはキャッシュ領域というものがあり
	 * ヒープのデータを参照する際や、ヒープのデータを更新する際の全ての操作において、キャッシュが使われます。
	 * つまり、ヒープ上のデータを参照しても、キャッシュ上の古いデータを参照している可能性があり、
	 * ヒープ上のデータを更新しても、キャッシュ上のデータを更新しているだけで
	 * 別のスレッドからは、更新前の古いデータが見えている可能性があります。
	 * このキャッシュは、あるタイミングでヒープと同期をとり
	 * 最新の状態になりますが、そのタイミングを知ることはできません。 
	 */
	
	private int LoadExitedScene;
	private boolean nowLoading;
	private boolean nowFading;
	
	private ImageLoader iLoadertmp;
	
	/** ローディング中に表示するクラス **/
	public Loading(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public void render(Graphics2D g)
	{
		super.render(g);
		//System.out.println("Now loading....");
	}
	
	public void initResources() 
	{
		super.initResources();
		LoadExitedScene = -1;
		nowLoading = false;
		nowFading = false;
	}
	
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		if( !nowLoading )
		{	
			if( !nowFading )
			{	// まだロード始まってないお
				if( LoadExitedScene == 定数.SCENE_GAME01 )
				{
					iLoadertmp = ImageLoader.Get_Instance(owner);
					iLoadertmp.start();		// ロード開始
					nowLoading = true;
				}
				if ( LoadExitedScene == 定数.SCENE_LOAD )
				{
					iLoadertmp = ImageLoader.Get_Instance(owner);				
					iLoadertmp.start();		// ロード開始
					nowLoading = true;
				}
			}
		}
		else
		{ 	// すでにローディングスレッドが走ってるお
			if( !iLoadertmp.isAlive() )
			{	//スレッド処理が終了したら
				owner.setImageLoader( iLoadertmp );
				nowLoading = false;
				nowFading = true;
				this.FadeOutToScene( LoadExitedScene );
			}
		}
	}
		
	/**
	 * ローディング処理終了後飛ばすシーンナンバをセットする
	 * @param sceneno
	 */
	public void setNextSceneNo( int sceneno )
	{
		LoadExitedScene = sceneno;
	}
	
	/**
	 * 終了処理
	 */
	public void DestractFadeOut()
	{
		nowFading = false;
		LoadExitedScene = -1;
	}
}
