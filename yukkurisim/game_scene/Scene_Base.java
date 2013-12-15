package game_scene;

import gamestatus.Const_Value;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import com.golden.gamedev.object.Background;
import yukkurisim.yukkurisim_main;

public abstract class Scene_Base {
	protected yukkurisim_main owner;		//�I�[�i�[�Q�[���ւ̎Q��
	static Const_Value	�萔;
	protected float alpha = 0.0f;
	private int NextSceneNo;
	private boolean RenderedZeroAlpha;	// �A���t�@�l�O�ł�render���I�������t���O�i�t�F�[�h�A�E�g����������
	private int Sleeptype;
    protected Background background; 			// �o�b�N�O���E���h
    
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
    	this.background = owner.background;		// ��{�I�ɂ̓I�[�i�[�o�b�N�O���E���h�i���n��p���邪�A���̐F�̏ꍇ��|���΂悢�j
    }
	
	public	Scene_Base(yukkurisim_main own)
	{
		this.owner = own;
		�萔 = new Const_Value();
				
	}
	
	public void update(long elapsedTime)
	{
		if( FadeOutFLG )
		{
			alpha -= 0.01f;
			if(RenderedZeroAlpha)
			{
				DestractFadeOut();	// �I������
				owner.JumpSceneto(NextSceneNo);
			}
		}
		else if( FadeInFLG )
		{
			alpha += 0.01f;
		}
		
	}
	
	/** �K���͂��߂Ƀo�b�N�O���E���h��render���� */
	/** ���꒴�d�v **/
	public void render(Graphics2D g)
	{
		this.background.render(g);
		if( FadeInFLG||FadeOutFLG )
		{
			//�A���t�@�l��0.0-1.0
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

	        // �A���t�@�l�Z�b�g
        	//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
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
	
	/**
	 * �t�F�[�h�A�E�g���؂������ɃR�[�������I������
	 *
	 */
	public void DestractFadeOut()
	{
		
	}
}
