package gamestatus;

public class Const_Value {
	// �萔�Ǘ��N���X
	public final	int	SCREEN_WIDTH = 1024;		//��ʕ�
	public final	int ��ʕ� = SCREEN_WIDTH;
	
	public final	int	SCREEN_HEIGHT = 768;	//��ʍ���
	public final	int ��ʍ��� = SCREEN_HEIGHT;
	
	public final	int SCREEN_CELL_WIDTH = 22;				//��ʉ��Z����
	public final	int ��ʉ��Z���� = SCREEN_CELL_WIDTH;	//��ʉ��Z����
	
	public final	int SCREEN_CELL_HEIGHT = 44;			//��ʏc�Z����
	public final	int ��ʏc�Z���� = SCREEN_CELL_HEIGHT;	//��ʏc�Z����

	public final	int MAPCHIP_WIDTH	=	102;				//�}�b�v�`�b�v�̉���
	public final	int �}�b�v�`�b�v���� = MAPCHIP_WIDTH;
	public final	int MAPCHIP_HEIGHT	=56;
	public final	int �}�b�v�`�b�v�c�� = MAPCHIP_HEIGHT;
	
	public final	int MAX_STATUS_VALUE	=	100;		//���������̃X�e�[�^�X�l��MAX
	public final	int C_�X�e�[�^�X�ő�l	=	MAX_STATUS_VALUE;
	public final	int MAX_HUNGRY_VALUE	=	10000;		//�����x��MAX
	public final	int C_���Ȃ��ő�l		=	MAX_HUNGRY_VALUE;
	
	public final	int	C_START_CASH_VALUE	=	1000;		//�������̏����l
	public final	int �����������l		=	C_START_CASH_VALUE;
	
	public final	int	STATE_MOVING = 1;
	public final	int �ړ��� = STATE_MOVING;
	public final	int STATE_WAITING = 2;
	public final	int �ҋ@�� = STATE_WAITING;
	public final	int STATE_EATING = 3;
	public final	int �H���� = STATE_EATING;
	public final	int STATE_FARMING = 4;
	public final	int �_�ƒ� = STATE_FARMING;
	public final	int STATE_FARMED = 5;
	public final	int �_�ƌ�ҋ@�� = STATE_FARMED;
	public final	int STATE_HANTED = 6;
	public final	int ����ҋ@�� = STATE_HANTED;

	//****************���S�n���(state>=100�Ŏ��S����)
	public final	int STATE_DEAD = 100;
	public final	int ���S = STATE_DEAD;
	public final	int STATE_DEAD_DESEASE = 101;
	public final	int �a�� = STATE_DEAD_DESEASE;

	//****************�^�C�v�̒�`�@��������
		public final int	TYPE_NULL						=	-999;	//	�g�p�s��
		public final int	TYPE_���g�p						=	TYPE_NULL;
	
		public final int	TYPE_ALL_DENY					=	0	;	//	���ׂĂ��\
		public final int	TYPE_PASS_DENY					=	1	;	//	�ʍs�̂݉\
		public final int	TYPE_ACTION_DENY				=	2	;	//	���̂݉\
		public final int	TYPE_FLYING_ONLY_PASS_DENY		=	3	;	//	��s���j�b�g�̂ݒʍs�\
		public final int	TYPE_FLYING_ONLY_ACTION_DENY	=	4	;	//	��s���j�b�g�̂݊��\
		public final int	TYPE_FLYING_ONLY_ALL_DENY		=	5	;	//	��s���j�b�g�݂̂��ׂĂ��\

		// �}�b�v�`�b�v�^�C�v���{��萔��`
		public final int	TYPE_���ׂĂ��\	=	TYPE_ALL_DENY	;
		public final int	TYPE_�ʍs�̂݉\	=	TYPE_PASS_DENY	;
		public final int	TYPE_���̂݉\	=	TYPE_ACTION_DENY	;
		public final int	TYPE_��s���j�b�g�̂ݒʍs�\	=	TYPE_FLYING_ONLY_PASS_DENY	;
		public final int	TYPE_��s���j�b�g�̂݊��\	=	TYPE_FLYING_ONLY_ACTION_DENY	;
		public final int	TYPE_��s���j�b�g�݂̂��ׂĂ��\	=	TYPE_FLYING_ONLY_ALL_DENY	;
	
		// �L�����N�^�[�n
		public final int C_TYPE_COUNT		=	7;							// �L�����N�^�[�n�^�C�v���S���ŉ����
		public final int TYPE_�L�����N�^�[�n�^�C�v���� = C_TYPE_COUNT;	// ��`����Ă��邩�i���₵����X�V���邱�Ɓj
		

		//****************�L�����N�^�[�n�I�u�W�F�N�g�ԍ��̒�`�@��������		
		public final int C_TYPE_YUKKURI	=	100;
		public final int TYPE_�������object	=	C_TYPE_YUKKURI;
		public final int C_TYPE_MARISA	=	101;
		public final int TYPE_�܂肳object		=	C_TYPE_MARISA;

		//****************���z���n�I�u�W�F�N�g�ԍ��̒�`�@��������
		public final int C_TYPE_TREE		=	120;
		public final int C_TYPE_HOUSE		=	121;
		public final int C_TYPE_ENTRY		=	122;
		public final int C_TYPE_ROCK		=	123;
		public final int C_TYPE_KAKINE	=	124;
		public final int C_TYPE_FARM		=	125;
		public final int TYPE_��object			=	C_TYPE_TREE;
		public final int TYPE_��object			=	C_TYPE_HOUSE;
		public final int TYPE_�����object		=	C_TYPE_ENTRY;
		public final int TYPE_��object			=	C_TYPE_ROCK;
		public final int TYPE_�_��object			=	C_TYPE_KAKINE;
		public final int TYPE_�_�nobject			=	C_TYPE_FARM;

		public final int TYPE_object_�L�����N�^�ƌ��z���ԍ��~���l =120;
	//****************�V�[���ԍ��̒�`�@��������
		public final int SCENE_TITLE	=	1;		//�^�C�g��
		public final int SCENE_GAME01 =	2;		//�Q�[���P
		public final int SCENE_LOAD	=	3;		//���[�h�V�[��
		public final int SCENE_LOADING=	10;		//���[�f�B���O�\��
		public final int SCENE_TEST	=	10000;	//�e�X�g�V�[��
		public final int SCENE_TEST_02=	10001;	//�e�X�g�V�[��02
	
	//****************�V�[���ԍ��̒�`�@�����܂�
		
		public final int C_MOJI_THRESHOLD =		2;
		public final int ���b�Z�[�W�e�L�X�g���x = C_MOJI_THRESHOLD;
		
		public final int C_SCROLLING_MAX_X 	=	2000;
		public final int �X�N���[���ő�͈�X 	= 	C_SCROLLING_MAX_X;
		public final int C_SCROLLING_MAX_Y 	=	2000;
		public final int �X�N���[���ő�͈�Y 	= 	C_SCROLLING_MAX_Y;
		public final int C_SCROLLING_MIN_X 	=	-2000;
		public final int �X�N���[���ŏ��͈�X 	= 	C_SCROLLING_MIN_X;
		public final int C_SCROLLING_MIN_Y 	=	-2000;
		public final int �X�N���[���ŏ��͈�Y 	= 	C_SCROLLING_MIN_Y;
		public final int C_SCROLLING_PLAY		=	10;					// �X�N���[���J�n�ƂȂ��ʒ[�́u�V�сv
		public final int �X�N���[���V��		=	C_SCROLLING_PLAY;	// �u�V�сv���ĉp�ꂾ�Ɓuplay�v�Ȃ񂾂ˁ[�A�ւ��[
																		// �J�[�\���������I ��ʒ[�����I

		//****************�T�u�E�C���h�E�E�{�^���ԍ��̒�`�@��������
		public final int BUTTON_ID_BUILD_NULL	=	0;
		public final int �{�^��ID_�k��		=	BUTTON_ID_BUILD_NULL;
		public final int BUTTON_ID_BUILD_TREE	=	10;
		public final int �{�^��ID_�؃{�^��	=	BUTTON_ID_BUILD_TREE;
		public final int BUTTON_ID_BUILD_HOUSE=	11;
		public final int �{�^��ID_�ƃ{�^��	=	BUTTON_ID_BUILD_HOUSE;
		public final int BUTTON_ID_BUILD_FARM=	12;
		public final int �{�^��ID_�_�n�{�^��	=	BUTTON_ID_BUILD_FARM;
		
		public final int BUTTON_ID_BUILD_REMOVE =	999;
		public final int �{�^��ID_�P���{�^��=	BUTTON_ID_BUILD_REMOVE;
		public final int BUTTON_ID_BUILD_EXIT =	9999;
		public final int �{�^��ID_����{�^��=	BUTTON_ID_BUILD_EXIT;
		
		public final int BUTTON_ID_DIALOG_OK	=	500;
		public final int �{�^��ID_�_�C�A���OOK=	BUTTON_ID_DIALOG_OK;
		public final int BUTTON_ID_DIALOG_YES	=	501;
		public final int �{�^��ID_�_�C�A���OYES=	BUTTON_ID_DIALOG_YES;
		public final int BUTTON_ID_DIALOG_NO	=	502;
		public final int �{�^��ID_�_�C�A���ONO=	BUTTON_ID_DIALOG_NO;
	
		public final int BUTTON_ID_ITEM_01	=	600;	// �A�C�e���{�^���P
		public final int �{�^��ID_�A�C�e��01	=	BUTTON_ID_ITEM_01;
		public final int BUTTON_ID_ITEM_02	=	601;	// �A�C�e���{�^���Q
		public final int �{�^��ID_�A�C�e��02	=	BUTTON_ID_ITEM_02;
		public final int BUTTON_ID_ITEM_03	=	602;	// �A�C�e���{�^���R
		public final int �{�^��ID_�A�C�e��03	=	BUTTON_ID_ITEM_03;
		public final int BUTTON_ID_ITEM_04	=	603;	// �A�C�e���{�^���S
		public final int �{�^��ID_�A�C�e��04	=	BUTTON_ID_ITEM_04;
		public final int BUTTON_ID_ITEM_05	=	604;	// �A�C�e���{�^���T
		public final int �{�^��ID_�A�C�e��05	=	BUTTON_ID_ITEM_05;
		public final int BUTTON_ID_ITEM_06	=	605;	// �A�C�e���{�^���U
		public final int �{�^��ID_�A�C�e��06	=	BUTTON_ID_ITEM_06;
		
		public final int BUTTON_ID_TITLE_01	=	700;
		public final int �{�^��ID_�^�C�g��01	=	BUTTON_ID_TITLE_01;
		public final int BUTTON_ID_TITLE_02	=	701;
		public final int �{�^��ID_�^�C�g��02	=	BUTTON_ID_TITLE_02;
		
		//****************�T�u�E�C���h�E�E���x���ԍ��̒�`�@��������
		public final int LABEL_ID_BUILD_COST = 900;
		public final int ���x��ID_���z_��p=LABEL_ID_BUILD_COST;
		public final int LABEL_ID_BUILD_CASH = 901;
		public final int ���x��ID_���z_������=LABEL_ID_BUILD_CASH;
		
		public final int LABEL_ID_ACTION_SYUZOKU = 1000;
		public final int ���x��ID_�A�N�V����_�푰�� = LABEL_ID_ACTION_SYUZOKU;
		public final int LABEL_ID_ACTION_NOUGYOU = 1001;
		public final int ���x��ID_�A�N�V����_�_�� = LABEL_ID_ACTION_NOUGYOU;
		public final int LABEL_ID_ACTION_KARI = 1002;
		public final int ���x��ID_�A�N�V����_��� = LABEL_ID_ACTION_KARI;
		public final int LABEL_ID_ACTION_CHECK_KARI = 1003;
		public final int ���x��ID_�A�N�V����_����_��� = LABEL_ID_ACTION_CHECK_KARI;
		public final int LABEL_ID_ACTION_CHECK_NOUGYOU = 1004;
		public final int ���x��ID_�A�N�V����_����_�_�� = LABEL_ID_ACTION_CHECK_NOUGYOU;
		public final int LABEL_ID_ACTION_CHECK_ASOBI = 1005;
		public final int ���x��ID_�A�N�V����_����_�V�� = LABEL_ID_ACTION_CHECK_ASOBI;

		public final int LABEL_ID_STATUS_SYUZOKU = 1100;
		public final int ���x��ID_�X�e�[�^�X_�푰 =LABEL_ID_STATUS_SYUZOKU;
		public final int LABEL_ID_STATUS_TAIRYOKU = 1101;
		public final int ���x��ID_�X�e�[�^�X_�̗� = LABEL_ID_STATUS_TAIRYOKU;
		public final int LABEL_ID_STATUS_KOKORO	= 1102;
		public final int ���x��ID_�X�e�[�^�X_������ = LABEL_ID_STATUS_KOKORO;
		public final int LABEL_ID_STATUS_TIKARA = 1103;
		public final int ���x��ID_�X�e�[�^�X_������ = LABEL_ID_STATUS_TIKARA;
		public final int LABEL_ID_STATUS_AMASA = 1104;
		public final int ���x��ID_�X�e�[�^�X_�Â� = LABEL_ID_STATUS_TIKARA;
		public final int LABEL_ID_STATUS_ONAKA = 1105;
		public final int ���x��ID_�X�e�[�^�X_���Ȃ� = LABEL_ID_STATUS_ONAKA;
		public final int LABEL_ID_STATUS_DAYS = 1106;
		public final int ���x��ID_�X�e�[�^�X_���� = LABEL_ID_STATUS_DAYS;

		//****************�T�u�E�C���h�E�E���̑��̒�`�@��������
		public final int OTHER_ID_ACTION_IMAGE = 1200;
		public final int ���̑�ID_�摜�\�� = OTHER_ID_ACTION_IMAGE;
		public final int CHECKBOX_ID			=	1300;
		public final int �`�F�b�N�{�b�N�Xid	=	CHECKBOX_ID;

		//****************�琬�p�[�g�Ɏ󂯓n���푰�ʃA�N�V������`�@��������
		public final int ACTION_KARI = 1;
		public final int �A�N�V����_��� = ACTION_KARI;
		public final int ACTION_FARM = 2;
		public final int �A�N�V����_�_�� = ACTION_FARM;
		public final int ACTION_PLAY = 3;
		public final int �A�N�V����_�V�� = ACTION_PLAY;
		
		

		//****************�J�[�\�����g�p����ۂ̖߂菈���敪�@��������
		public final int COMEBACK_CURSOR_BUILD_01_TREE		=	0;
		public final int �J�[�\������_�r���h�E�C���h�E01_��	=	0;
		public final int COMEBACK_CURSOR_BUILD_01_HOUSE		=	1;
		public final int �J�[�\������_�r���h�E�C���h�E01_��	=	1;
		public final int COMEBACK_CURSOR_BUILD_01_FARM		=	2;
		public final int �J�[�\������_�r���h�E�C���h�E01_�_�n	=	2;
		
		
		//****************�^�C�}�֘A�@��������
		public final int ����̒���			=		8000;				// �琬�p�[�g�̒���
		public final int TIME_COUNT_OF_DAY	=		����̒���;			// �琬�p�[�g�̒���
		public final int �p�[�g_�{��_���z�敪	=		0;
		public final int SISAKU_BUILD_KBN		=		�p�[�g_�{��_���z�敪;
		public final int �p�[�g_�{��_�s���敪	=		1;
		public final int SISAKU_ACTION_KBN	=		�p�[�g_�{��_�s���敪;
		public final int �p�[�g_�琬�敪		=		2;
		public final int IKUSEI_KBN			=		�p�[�g_�琬�敪;
		public final int �p�[�g_�P���敪		=		3;
		public final int SYUGEKI_KBN			=		�p�[�g_�P���敪;
		
		//****************�A�C�e���֘A�@��������
		public final int �A�C�e���ő�ԍ�		=		13;
		// �A�C�e���ԍ�0�͗\���p�A�����Ɏg����������Ȃ����A�g��Ȃ���������Ȃ�
		public final int �A�C�e��_������ = 1;        //
		public final int �A�C�e��_�������t�[�h = 2;        //
		public final int �A�C�e��_�|���E�f�E����� = 3;        //
		public final int �A�C�e��_�V���[�N���[�� = 4;        //
		public final int �A�C�e��_�I�����W�W���[�X = 5;        //
		public final int �A�C�e��_�ԉ� = 6;        //
		public final int �A�C�e��_���ق� = 7;        //
		public final int �A�C�e��_����݂傤���� = 8;        //
		public final int �A�C�e��_�ق��� = 9;        //
		public final int �A�C�e��_�n�T�~ = 10;        //
		public final int �A�C�e��_�u���V = 11;        //
		public final int �A�C�e��_��݂��Ԃ��� = 12;        //
		public final int �A�C�e��_�v�΂̂����� = 13;        //

		//****************�摜�ԍ���`�@��������
		public final int �摜�ԍ�_�������ꂢ�ޕ��� = 31000;
		public final int �摜�ԍ�_�������ꂢ�ޑ҂� = 31001;
		public final int �摜�ԍ�_�������ꂢ�ސH�ׂ� = 31002;
		public final int �摜�ԍ�_�������ꂢ�ޕa�� = 31003;
		public final int �摜�ԍ�_�������ꂢ�ގ��S = 31004;
		public final int �摜�ԍ�_�������ꂢ�ޕ���_���] = 31005;
		public final int �摜�ԍ�_�������ꂢ�ޑ҂�_���] = 31006;
		public final int �摜�ԍ�_�������ꂢ�ސH�ׂ�_���] = 31007;
		public final int �摜�ԍ�_�������ꂢ�ޕa��_���] = 31008;
		public final int �摜�ԍ�_�������ꂢ�ގ��S_���] = 31009;
			
		public final int �摜�ԍ�_�J�[�\�� = 31100;
		public final int �摜�ԍ�_��1 = 31110;
		public final int �摜�ԍ�_��2 = 31111;
		public final int �摜�ԍ�_��3 = 31112;
		public final int �摜�ԍ�_��4 = 31113;
		public final int �摜�ԍ�_��5 = 31114;
		public final int �摜�ԍ�_��6 = 31115;
		public final int �摜�ԍ�_�^�C��0 = 31120;
		public final int �摜�ԍ�_����1 = 31121;
		public final int �摜�ԍ�_����2 = 31122;
		public final int �摜�ԍ�_��1 = 31123;
		public final int �摜�ԍ�_��2 = 31124;
		public final int �摜�ԍ�_�G���g�����X = 31125;
		public final int �摜�ԍ�_�� = 31150;
		public final int �摜�ԍ�_�_�n = 31151;
		//************************ Widget�n�������� ***********************************//
		public final int �摜�ԍ�_�`�F�b�N�{�b�N�X�I�� = 31200;
		public final int �摜�ԍ�_�`�F�b�N�{�b�N�X�I�t = 31201;
		public final int �摜�ԍ�_�_�C�A���O�E�B���h�E = 31202;
		public final int �摜�ԍ�_���b�Z�[�W�E�B���h�E = 31203;
		public final int �摜�ԍ�_�^�C�} = 31204;
		public final int �摜�ԍ�_���j�� = 31205;
		public final int �摜�ԍ�_�`�F�b�N�{�^�� = 31206;
		public final int �摜�ԍ�_�V���b�v�{�^�� = 31207;
		public final int �摜�ԍ�_�g���b�v�{�^�� = 31208;
		public final int �摜�ԍ�_�r���h�{�^�� = 31209;
		public final int �摜�ԍ�_�N���b�V���{�^�� = 31210;
		public final int �摜�ԍ�_�f�[�^�{�^�� = 31211;
		public final int �摜�ԍ�_�N�C�b�N�{�^�� = 31212;
		public final int �摜�ԍ�_�p�j�b�V���{�^�� = 31213;
		public final int �摜�ԍ�_���u�{�^�� = 31214;
		public final int �摜�ԍ�_�A�C�e���{�^�� = 31215;
		public final int �摜�ԍ�_�r���h�E�C���h�E = 31230;
		public final int �摜�ԍ�_�؃{�^�� = 31231;
		public final int �摜�ԍ�_�ƃ{�^�� = 31232;
		public final int �摜�ԍ�_����{�^�� = 31233;
		public final int �摜�ԍ�_�P���{�^�� = 31234;
		public final int �摜�ԍ�_�_�n�{�^�� = 31235;
		public final int �摜�ԍ�_�A�N�V�����E�C���h�E = 31250;
		public final int �摜�ԍ�_�X�e�[�^�X�E�C���h�E = 31270;
		public final int �摜�ԍ�_�A�C�e���E�C���h�E = 31300;
		public final int �摜�ԍ�_�_�C�A���OOK = 31320;
		public final int �摜�ԍ�_�^�C�}_������ = 31221;
		//************************ �A�C�e���n�������� ***********************************//
		public final int �摜�ԍ�_�A�C�e���n�e�i = 31340;
		
		
		
		//************************ ���[�f�B���O��ʏ����敪 *****************************//
		public final int ���[�f�B���O_���W�b�N1 = 1;
}