
package yukkurisim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ColorBackground;
// �萔�N���X
import gamestatus.Const_Value;

// �V�[���N���X
import game_scene.*;

import java.io.*;
public class yukkurisim_main extends Game implements Serializable {	
    public Background background; 							// �w�i
    public UniqueIdManager IdMan = new UniqueIdManager();	// �I�u�W�F�N�gID�̃}�l�[�W��
    private ImageLoader iLoader;							// �C���[�W���[�_�Q��
    private int SceneNo;									// �V�[���̊Ǘ��ԍ��B

    TitleScene				title;
    Game01					gamescene;						//�j���[�Q�[���̃V�[��
    LoadGame				loadscene;						//�Q�[���̃��[�h�V�[��
    Loading					nowloading;						//�Q�[���̃��[�f�B���O�V�[��
    
    /**
     * ������
     */
    public Physical_Law_Facade phy_law;
    static  Const_Value	�萔;

    @Override
	public void initResources() {
    	background = new ColorBackground(Color.black, �萔.��ʕ�, �萔.��ʍ���);
    	phy_law = Physical_Law_Facade.Get_Instance(this);
    	
    	/*********** �V�[���̐錾 �������� **************/
    	title 	= new TitleScene(this);				// �^�C�g�����
    	nowloading = new Loading(this);				// ���[�f�B���O

    	//�V�[���̏�����
    	title.initResources();
    	nowloading.initResources();
    	
    	/*********** �V�[���̐錾 �����܂� **************/
    	SceneNo = �萔.SCENE_TITLE;		//�^�C�g���V�[������X�^�[�g
    	
    	mouseUPFlag = false;
    	lastMouseState = false;
    }

    /**
     * �X�V
     */
    @Override
	public void update(long elapsedTime) {
    	updateMouseUPFlag();	// MouseUP�C�x���g�Ď�
    	
    	//background�̃����_�����O�̓V�[�����ł��ׂ�	    	
        if(SceneNo == �萔.SCENE_TITLE)
        {
        	title.update(elapsedTime);
        }
        if( SceneNo == �萔.SCENE_GAME01)
        {
        	gamescene.update(elapsedTime);
        }
        if( SceneNo == �萔.SCENE_LOAD )
        {
        	loadscene.update(elapsedTime);
        }
        if( SceneNo == �萔.SCENE_LOADING)
        {
        	nowloading.update(elapsedTime);
        }
        	
        // ESC�ŏI��        
        if (keyPressed(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
    }

    /**
     * �����_�����O
     */
    @Override
	public void render(Graphics2D g) {

    	if(SceneNo == �萔.SCENE_TITLE)
        {
    		title.render(g);
        }
        if(SceneNo == �萔.SCENE_GAME01)
        {
        	gamescene.render(g);
        }
        if(SceneNo == �萔.SCENE_LOAD)
        {
        	loadscene.render(g);
        }
        if(SceneNo == �萔.SCENE_LOADING)
        {
        	nowloading.render(g);
        }

    }

    public static void main(String[] args) {
        �萔 = new Const_Value();
    	GameLoader 	game = new GameLoader();
        // 1024x768�̃t���X�N���[�����[�h
        game.setup(new yukkurisim_main(), new Dimension(�萔.��ʕ�, �萔.��ʍ���), false);
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
    	
    	if( (no==�萔.SCENE_GAME01)&&(gamescene==null) )
    	{
        	gamescene = new Game01(this);					//�j���[�Q�[���̃V�[��
        	gamescene.initResources();
    	}
    	if( (no==�萔.SCENE_LOAD)&&(loadscene==null) )
    	{
        	loadscene = new LoadGame(this);				//�Q�[���̃��[�h�V�[��
        	loadscene.initResources();
    	}
    }

    /**
     * �Q�[���̃��C���V�[�������݃t�F�[�h����������Ԃ�
     * @return
     */
	public boolean GameIsFading()
	{
    	if(SceneNo == �萔.SCENE_TITLE)
        {
    		return title.GameIsFading();
        }
        if(SceneNo == �萔.SCENE_GAME01)
        {
        	return gamescene.GameIsFading();
        }
        if(SceneNo == �萔.SCENE_LOAD)
        {
        	return loadscene.GameIsFading();
        }
        if(SceneNo == �萔.SCENE_LOADING)
        {
        	return nowloading.GameIsFading();
        }
        
        return false;
	}

	/** ���[�f�B���O�N���X����󂯓n�����C���[�W���[�_�N���X�ւ̃C���X�^���X�󂯎�葋�� **/
	public void setImageLoader( ImageLoader i )
	{
		iLoader = i;
	}
	
	public ImageLoader getImageLoader()
	{
		return iLoader;
	}
	
	
	/**
	 * ���[�f�B���O�V�[���ɓ���O�ɁA���[�h�I����̃V�[����ʒm���Ă���
	 * @param sceneno
	 */
	public void setLoadEndedScene( int sceneno )
	{
		nowloading.setNextSceneNo(sceneno);
	}
	
	private boolean mouseUPFlag;		// MouseUP�̏u�Ԃ�true�ƂȂ�
	private boolean lastMouseState;	// �P���[�v�O�̃}�E�X�̏�Ԃ�ێ�����

	private void updateMouseUPFlag()
	{
		if( (!this.bsInput.isMouseDown(1)) && lastMouseState)	// �O���Ԃ̓I���ŁA���ݏ�Ԃ̓I�t��
		{	// �����ꂽ�u��
			mouseUPFlag = true;
		}
		else
		{
			mouseUPFlag = false;
		}
		lastMouseState = this.bsInput.isMouseDown(1);	// �����[�v�p�ɕێ�
	}
	
	/**
	 * �}�E�X�������ꂽ�u�Ԃ�true�ƂȂ�B
	 * @return
	 */
	public boolean isMouseUp()
	{
		return mouseUPFlag;
	}
}
