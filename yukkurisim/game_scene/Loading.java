package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import yukkurisim.ImageLoader;
import yukkurisim.Object_base;
import yukkurisim.OtherLoader;
import yukkurisim.Other_Object;
import yukkurisim.Widget_Base;
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
	private boolean 		nowLoading;
	private boolean 		nowFading;
	
	private Object_base	RotateYukkuri;		// 回転しているゆっくり
	private Widget_Base	NowLoadingText;		// 饅頭祈祷中...の文字
	private boolean		isFadeOutNow;		// 文字がフェードアウトしているかを制御する
	private BufferedImage[] yImage;			// ゆっくりの画像
	private int			rotatesize;			// 回転角度(360度法)
	
	private ImageLoader 	iLoadertmp;
	private OtherLoader	otmp;
	/** ローディング中に表示するクラス **/
	public Loading(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	@Override
	public void render(Graphics2D g)
	{
		super.render(g);
		NowLoadingText.render(g);
		RotateYukkuri.render(g);
	}
	
	@Override
	public void initResources() 
	{
		super.initResources();
		LoadExitedScene = -1;
		nowLoading = false;
		nowFading = false;
		rotatesize = 0;
		yImage = owner.getImages("image/reimuwait.gif",6,1);
		
		RotateYukkuri = new Other_Object(owner , yImage);
		NowLoadingText = new Widget_Base(	owner,
				"饅頭祈祷中...",
				0,
				0,
				null);
		NowLoadingText.setLocation(定数.画面幅 - NowLoadingText.getWidth() - 10, 5 + ( RotateYukkuri.getHeight()/2 ) - ( NowLoadingText.getHeight()/2 ));
		NowLoadingText.setAlpha(0);
		NowLoadingText.setFontColor(Color.white);		// 文字色は白
		NowLoadingText.setBackground(this.background);
		NowLoadingText.setActive(true);
		isFadeOutNow = false;							// 文字がフェードアウトしているかを制御する
		RotateYukkuri.setLocation(NowLoadingText.getX() - RotateYukkuri.getWidth() , 5);
		RotateYukkuri.setAnimate(true);
		RotateYukkuri.setBackground(this.background);

	}
	
	@Override
	public void update(long elapsedTime)
	{
		AffineTransform at = new AffineTransform();
		super.update(elapsedTime);
		if(isFadeOutNow)
		{
			NowLoadingText.setAlpha(NowLoadingText.getAlpha() - 0.02f);
			if( NowLoadingText.getAlpha() <= 0.0f )
			{
				NowLoadingText.setAlpha(0.0f);
				isFadeOutNow = false;
			}
			
			at.setToRotation(Math.toRadians(rotatesize+=2),(yImage[1].getWidth() / 2),(yImage[1].getHeight()/2));	// 回転のセット
			if( rotatesize >= 360 )
			{
				rotatesize = 0;
			}
			AffineTransformOp atOp = new AffineTransformOp(at, null);

			BufferedImage[] revimg = new BufferedImage[yImage.length];
			for(int i=0;i<yImage.length;i++)
			{
				revimg[i] = atOp.filter(yImage[i], null);	// 実際に画像変換を行ってるのはココ
			}
			RotateYukkuri.setImages(revimg);
		}
		else
		{
			NowLoadingText.setAlpha(NowLoadingText.getAlpha() + 0.02f);
			if( NowLoadingText.getAlpha() >= 1.0f )
			{
				NowLoadingText.setAlpha(1.0f);
				isFadeOutNow = true;
			}
			
			at.setToRotation(Math.toRadians(rotatesize+=2),(yImage[1].getWidth() / 2),(yImage[1].getHeight()/2));	// 回転のセット

			if( rotatesize >= 360 )
			{
				rotatesize = 0;
			}
			AffineTransformOp atOp = new AffineTransformOp(at, null);

			BufferedImage[] revimg = new BufferedImage[yImage.length];
			for(int i=0;i<yImage.length;i++)
			{
				revimg[i] = atOp.filter(yImage[i], null);	// 実際に画像変換を行ってるのはココ
			}
			RotateYukkuri.setImages(revimg);
		}
		if( !nowLoading )
		{	
			if( !nowFading )
			{	// まだロード始まってないお
				if( LoadExitedScene == 定数.SCENE_GAME01 )
				{
					iLoadertmp = ImageLoader.Get_Instance(owner);
					iLoadertmp.start();		// ロード開始
					otmp = new OtherLoader(owner);
					otmp.setLogicFlag(定数.ローディング_ロジック1);		// 現在ロジックを分ける必要は生じていないが念のため
					otmp.start();
					
					nowLoading = true;
				}
				if ( LoadExitedScene == 定数.SCENE_LOAD )
				{
					iLoadertmp = ImageLoader.Get_Instance(owner);				
					iLoadertmp.start();		// ロード開始
					otmp = new OtherLoader(owner);
					otmp.setLogicFlag(定数.ローディング_ロジック1);		// 現在ロジックを分ける必要は生じていないが念のため
					otmp.start();
					nowLoading = true;
				}
			}
		}
		else
		{ 	// すでにローディングスレッドが走ってるお
			boolean AllThreadisExit = (!iLoadertmp.isAlive())&&(!otmp.isAlive());
			
			if( AllThreadisExit )
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
	@Override
	public void DestractFadeOut()
	{
		super.DestractFadeOut();
		nowFading = false;
		LoadExitedScene = -1;
		
	}
}
