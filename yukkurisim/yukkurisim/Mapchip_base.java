package yukkurisim;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import yukkurisim.yukkurisim_main;

public class Mapchip_base extends Object_base {

	private int my_chiptype;		//�`�b�v�̑����ȂǂɊւ���
	
	
	public Mapchip_base(yukkurisim_main own) {
		super(own);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Mapchip_base(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Mapchip_base(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	public Mapchip_base(yukkurisim_main own, BufferedImage[] arg0, double arg1, double arg2) {
		super(own, arg0, arg1, arg2);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	
	/**
	 * �}�b�v�`�b�v�N���X�̒ʏ�g�p�R���X�g���N�^�B
	 * @param own
	 * @param arg_img
	 * @param arg_x
	 * @param arg_y
	 * @param arg_chiptype
	 */
	public Mapchip_base(	yukkurisim_main	own,
							BufferedImage[]	arg_img, 
							int 			arg_x, 
							int 			arg_y , 
							int 			arg_chiptype
						) 
	{
		super(own, arg_img);	//�摜�݂̂̐e�R���X�g���N�^���R�[������B
		this.SetCellLocation(arg_x, arg_y);
		this.SetChipType(arg_chiptype);
		
		this.initialize();
	}

	public void initialize()
	{
		this.setAnimate(false); 				// �A�j���[�V�������Ȃ��B

	}
	/**
	 * �}�b�v�`�b�v�̃Z���P�ʂł̈ʒu��ݒ肷��.
	 * @param loc_x
	 * @param loc_y
	 */
	public void SetCellLocation(int loc_x , int loc_y)
	{
		this.my_x = loc_x;
		this.my_y = loc_y;
	}
	
	/**
	 * �}�b�v�`�b�v�̃^�C�v��ݒ肷��.
	 * @param Chiptype
	 */
	public void SetChipType( int Chiptype )
	{
		this.my_chiptype = Chiptype;
	}

	@Override
	public void update(long elapsedTime)
	{
		// ���͂��̂܂܂̃T�C�Y�ŁA�c�͔���(25�j����
		if( this.my_y%2==1)
		{
			this.setLocation(this.my_x * �萔.�}�b�v�`�b�v����+(�萔.�}�b�v�`�b�v����/2), this.my_y * (( �萔.�}�b�v�`�b�v�c�� / 2 )-3));			
		}
		else
		{
			this.setLocation(this.my_x * �萔.�}�b�v�`�b�v����, this.my_y * (( �萔.�}�b�v�`�b�v�c�� / 2 )-3));
		}
			super.update(elapsedTime);
	}

	@Override
	public int Get_Type() {
		return this.my_chiptype;
	}

	@Override
	public void doClickEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
	
	@Override
	public void render(Graphics2D g)
	{
		if(this.getImages()!=null)
		{
			super.render(g);
		}
	}
	
	@Override
	public boolean isCharactor()
	{
		return false;	// ����̓L�����N�^�[�ł͂Ȃ�
	}

	@Override
	public void doOnMouseEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	
}
