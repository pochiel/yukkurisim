package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Comparator;

import yukkurisim.*;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;
import yukkurisim.Cursor_Manager;
import yukkurisim.ImageLoader;
import yukkurisim.ItemManager;
import yukkurisim.MapObject_Manager;
import yukkurisim.Object_base;
import yukkurisim.yukkurisim_main;
import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.Widget_Manager;
import yukkurisim.MapTileManager;

/*********************************************************
 * 
 * @author �|�`�G��
 *
 * �Q�[���V�[���P.
 *********************************************************/

public class LoadGame extends Scene_Base {
    MapObject_Manager 	OnMapObj;					// �얲���܂߂��S�ẴI�u�W�F�N�g
    												// �G���A��̖ؓ��̃I�u�W�F�N�g���܂܂��
    												// �����𕪂��ăO���[�s���O�ł��Ȃ��̂�
    												// �����_�����O�̊֌W�ł���B

    ItemManager			itemMan;					// �A�C�e���}�l�[�W��
    
    ADV_SpriteGroup_base	map1;					// �}�b�v
    Widget_Manager			window;					// �E�B���h�E�O���[�v
    
    ////////////////////////////////////////////////////////
    private int			Message_State = 1;		// ���b�Z�[�W�\���p�J�E���^
    private MapTileManager	MapMan;					// �}�b�v�}�l�[�W��
    private Cursor_Manager	OwnCursor;				// �J�[�\��

    ////////////////////////////////////////////////////////
    
    private Background 	background; // �w�i
    private ImageLoader	iLoader;	// �C���[�W���[�_
    ////////////////////////////////////////////////////////
   
	
	/*******************************************************
	 * <p>�R���X�g���N�^</p>
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
    public LoadGame( yukkurisim_main own )
    {
    	super(own);
    }
    @Override
	public void initResources() {
    	
    	this.background = new ColorBackground(Color.black, �萔.��ʕ�, �萔.��ʍ���);
    	
    	iLoader = ImageLoader.Get_Instance(owner);

    	//OnMapObj = new MapObject_Manager(owner,"YUKKURI_REIMU");
    	OnMapObj = owner.phy_law.Get_ObjectManager();
    	
    	/********** �}�b�v�̐錾 ***********************/
    	int[][] mapgrid = {
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
  
    	// maptilemanager��mapobjectmanager���쐬���A�����@���N���X�֓o�^����
    	MapMan = new MapTileManager(owner , mapgrid);		//�}�b�v�}�l�[�W���Ń}�b�v���쐬����B
    	map1 = MapMan.GetMapGroup();						//�}�b�v�}�l�[�W���Ń}�b�v���쐬����B
    	
    	// �A�C�e���}�l�[�W���N���X�̓o�^
    	this.itemMan = ItemManager.Get_Instance(owner);
    	
    	// �����@���N���X�ւ̓o�^
    	owner.phy_law.Set_Manager(MapMan, OnMapObj,itemMan);

    	// ************** �Z�[�u�f�[�^�̃��[�h ****************/
    	GameSaveLoader loader = new GameSaveLoader();
    	loader.LoadState(owner);
    	
    	/*********** Comparator���Z�b�g �������� **************/
    	/*********** �}�b�v��̃\�[�g�����̎w��͂����� *******/
    	OnMapObj.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(( s1.getY() + s1.getHeight() ) - ( s2.getY() + s2.getHeight()) );
    				}
    			}
    	);
    	/*********** Comparator���Z�b�g �����܂� **************/
    	
    	/*********** Background���Z�b�g �������� **************/

    	OnMapObj.setBackground(background);
    	map1.setBackground(background);
    	
    	/*********** Background���Z�b�g �����܂� **************/
    	
       	//window�O���[�v���擾
       	window = Widget_Manager.Get_Instance(owner).GetWindowGroup();
       	/*********** widget ���Z�b�g �����܂� **************/

       	OwnCursor = Cursor_Manager.Get_Instance(owner);		// �J�[�\���X�N���[��
    }
    
	@Override
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		background.update(elapsedTime);
		map1.update(elapsedTime);
		if(window.GTimer.isMoving())
		{
			OnMapObj.update(elapsedTime);
		}
		OnMapObj.doClickEvent();	// �N���b�N�C�x���g�̓^�C�}�̏�Ԃɋ��������ׂ��ł͂Ȃ�
		
		//���b�Z�[�W�E�C���h�E�n�Ƃ�
		window.update(elapsedTime);
		
		OwnCursor.update(elapsedTime);			// �J�[�\���X�N���[��
		
		owner.phy_law.checkCollision();			// �Փˌ��o
	}
	
	@Override
	public void render(Graphics2D g)
	{		
		super.render(g);
		//background.render(g);	//�o�b�N�O���E���h�̕`��
		map1.render(g);			//�}�b�v�̕`��
		OwnCursor.render(g);	//�J�[�\���̕`��
		OnMapObj.render(g);		//�}�b�v��̃I�u�W�F�N�g�̕`��
		window.render(g);		//���j���[���A�E�C���h�E�̕`��
	}
}
