 package yukkurisim;

import java.util.Comparator;
import java.util.Vector;

import gamestatus.Const_Value;
import yukkurisim.Object_base;

public class MapObject_Manager extends ADV_SpriteGroup_base{
	private  Const_Value �萔 = new Const_Value();
	private Object_base Map_grid[][];
	private int Map_style[][];
	//private static MapObject_Manager  myself;
	// �_�n���Ǘ�����z��
	private Vector<Object_base> FarmVector = new Vector<Object_base>();
	public Vector<Object_base> getFarmVector() {
		return FarmVector;
	}

	/******* �C���X�^���X�𓾂�(�e�C���X�^���X�ƈ�������) ********/
	/*public static synchronized MapObject_Manager Get_Instance( yukkurisim_main own , String g_name)
	{
		if( myself == null)
		{
			myself = new MapObject_Manager(own,g_name);
		}

		return myself;
	}*/

	
	/**
	 * �_�n�x�N�^��Ԃ��B
	 * @param own
	 * @param g_name
	 */
	public MapObject_Manager(yukkurisim_main own,String g_name)
	{
		super(own, g_name);
		
		Map_grid = new Object_base[�萔.��ʉ��Z����][�萔.��ʏc�Z����];
		Map_style = new int[�萔.��ʉ��Z����][�萔.��ʏc�Z����];
		
		for(int x = 0 ; x<�萔.��ʉ��Z���� ; x++)
		{
			for(int y = 0 ; y < �萔.��ʏc�Z���� ; y++)
			{
				Map_style[x][y] = 0;
				Map_grid[x][y] = null;
			}
		}
		/*********** �}�b�v��̃\�[�g�����̎w��͂����� *******/
		this.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(( s1.getY() + s1.getHeight() ) - ( s2.getY() + s2.getHeight()) );
    				}
    			}
    	);

	}
	
	// object�̊Ǘ����n�߂�B
	public void add(Object_base obj,int c_type)
	{
		try
		{
			Map_grid[obj.my_x][obj.my_y] = obj; //obj���i�[
			Map_style[obj.my_x][obj.my_y] = c_type;
			this.add(obj);
			if(obj.Get_Type()==�萔.TYPE_�_�nobject)
			{
				System.out.println("�_�n�ǉ���"+obj.my_x+","+obj.my_y);
				FarmVector.add(obj);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("obj x��"+obj.my_x+"  y��"+obj.my_y);
			System.exit(-1);
		}
	}
	
	// object�̊Ǘ����I���
	public void kill(int x, int y)
	{
		System.out.println("x->" + x + "   y->" + y);
		Map_grid[x][y] = null;
	}
	
	// object�ւ̎Q�Ƃ�Ԃ�
	public Object_base Get_obj_pointer( int x , int y )
	{
		return Map_grid[x][y];
	}

	// object�̃^�C�v��Ԃ�
	public int Get_obj_type( int x , int y )
	{
		//System.out.println(" == x->" + x + " , y->" + y + " ==");
		return Map_style[x][y];
	}

	// �Y���I�u�W�F�N�g���Y���}�b�v�ɐi���ł��邩��Ԃ�
	// MapTile�Ƃ̈Ⴂ�͊�{�i���u�\�v�ł���A�i���s�ȃI�u�W�F�N�g�𒀈�ݒ肷�鎖
	public boolean Check_able_to_Entry(int cell_x,int cell_y,int c_type)
	{
		if( c_type == �萔.TYPE_�������object )
		{	
			// �������I�u�W�F�N�g�̐i���\������������
			int t = this.Get_obj_type(cell_x, cell_y);
			if( t==�萔.TYPE_��object )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return true;
	}
};
