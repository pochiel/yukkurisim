package yukkurisim;

import gamestatus.Const_Value;

/**
 * �f�ވȊO�Ń��[�f�B���O�V�[���}���`�X���b�h���s���s�������ꍇ
 * �R�R�ɏ���
 * @author �|�`�G��
 *
 */
public class OtherLoader extends Thread {
	private yukkurisim_main owner;					//�I�[�i�[Game�C���X�^���X�ւ̎Q��
	private  Const_Value �萔 = new Const_Value();
	private volatile static OtherLoader myself;

	private int LogicFlag;		// �ǂ̏������s���������肷��t���O
	/**
	 * �R���X�g���N�^
	 * @param own
	 */
	public OtherLoader(yukkurisim_main own)
	{
		owner = own;
		LogicFlag = 0;
	}
	
	public void run() {
		if(LogicFlag==0)
		{
			// setLogicFlag���o�R���Ă��Ȃ���Ή������Ȃ�
		}
		else if(LogicFlag==�萔.���[�f�B���O_���W�b�N1)
		{
	    	MapObject_Manager OnMapObj = new MapObject_Manager(owner,"YUKKURI_REIMU");
			Physical_Law_Facade phfacade = Physical_Law_Facade.Get_Instance(owner);
			ItemManager itemman = ItemManager.Get_Instance(owner);
			
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
	    	MapTileManager MapMan = new MapTileManager(owner , mapgrid);		//�}�b�v�}�l�[�W���Ń}�b�v���쐬����B

			phfacade.Set_Manager(MapMan, OnMapObj, itemman);

			Widget_Manager.Get_Instance(owner);	// ���[�h����
	    	
	    	Cursor_Manager.Get_Instance(owner);		// ���[�h�����ł悢
		}
	}
	
	public void setLogicFlag(int s)
	{
		LogicFlag = s;
	}


}