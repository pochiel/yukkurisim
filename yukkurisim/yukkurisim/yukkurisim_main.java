
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

// �萔�N���X
import gamestatus.Const_Value;

// �V�[���N���X
import game_scene.*;

// �^�C�}�N���X
import gamestatus.GameTimer;
import java.io.*;
public class yukkurisim_main extends Game implements Serializable {	
    public Background background; 							// �w�i
    public UniqueIdManager IdMan = new UniqueIdManager();	// �I�u�W�F�N�gID�̃}�l�[�W��
    private ImageLoader iLoader;							// �C���[�W���[�_�Q��
    private int SceneNo;									// �V�[���̊Ǘ��ԍ��B

    TitleScene				title;
    Game01					gamescene;						//�j���[�Q�[���̃V�[��
    LoadGame				loadscene;						//�Q�[���̃��[�h�V�[��
    /**
     * ������
     */
    public Physical_Law_Facade phy_law;
    static  Const_Value	�萔;

    public void initResources() {
    	background = new ColorBackground(Color.black, �萔.��ʕ�, �萔.��ʍ���);
    	phy_law = Physical_Law_Facade.Get_Instance(this);
    	
    	/*********** �V�[���̐錾 �������� **************/
    	title 	= new TitleScene(this);				//�^�C�g�����
    	
    	//�V�[���̏�����
    	title.initResources();

    	/*********** �V�[���̐錾 �����܂� **************/
    	SceneNo = �萔.SCENE_TITLE;		//�^�C�g���V�[������X�^�[�g
    }

    /**
     * �X�V
     */
    public void update(long elapsedTime) {
    	
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
        	
        // ESC�ŏI��        
        if (keyPressed(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
    }

    /**
     * �����_�����O
     */
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
    	SceneNo = no;
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
        return false;

	}

}