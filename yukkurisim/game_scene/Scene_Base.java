package game_scene;

import gamestatus.Const_Value;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;

import yukkurisim.yukkurisim_main;

public abstract class Scene_Base {
	protected yukkurisim_main owner;		//オーナーゲームへの参照
	static Const_Value	定数;
	protected float alpha = 0.0f;
	private int NextSceneNo;
	private boolean RenderedZeroAlpha;	// アルファ値０でのrenderが終了したフラグ（フェードアウトが完了した
	private int Sleeptype;
    protected Background background; 			// バックグラウンド
    
	protected boolean FadeInFLG;
	protected boolean FadeOutFLG;
	
    public void initResources() 
    {
    	FadeInFLG = false;
    	FadeOutFLG = false;
    	alpha = 1.0f;
    	Sleeptype = 0;
    	NextSceneNo = 0;
    	RenderedZeroAlpha = false;
    	this.background = owner.background;		// 基本的にはオーナーバックグラウンド（黒地を用いるが、他の色の場合上掛けばよい）
    }
	
	public	Scene_Base(yukkurisim_main own)
	{
		this.owner = own;
		定数 = new Const_Value();
				
	}
	public void update(long elapsedTime)
	{
		if( FadeOutFLG )
		{
			alpha -= 0.01f;
			if(RenderedZeroAlpha)
			{
				owner.JumpSceneto(NextSceneNo);
			}
		}
		else if( FadeInFLG )
		{
			alpha += 0.01f;
		}
		
	}
	
	/** 必ずはじめにバックグラウンドをrenderする */
	/** これ超重要 **/
	public void render(Graphics2D g)
	{
		this.background.render(g);
		if( FadeInFLG||FadeOutFLG )
		{
			//アルファ値は0.0-1.0
	        if (alpha < 0.0f)
	        {
	        	alpha = 0.0f;
	        	RenderedZeroAlpha = true;
	        }
	        else if (alpha > 1.0f) 
	        {
	        	alpha = 1.0f;
	        	if(FadeInFLG)
	        	{
	        		FadeInFLG = false;
	        	}
	        }

	        // アルファ値セット
        	//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, alpha));
		}
	}
	
	private void FadeOutScreen()
	{
		FadeInFLG = false;
		FadeOutFLG = true;
	}
	
	public void FadeOutToScene( int SceneNo )
	{
		this.NextSceneNo = SceneNo;
		FadeOutScreen();
		
	}
	
	public void FadeInScreen()
	{
		FadeInFLG = true;
		FadeOutFLG = false;		
	}
	
	public boolean GameIsFading()
	{
		return FadeInFLG||FadeOutFLG;
	}
}
