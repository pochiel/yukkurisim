package yukkurisim;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class KariAction extends ItemGeneratorActionBase {

	private static KariAction myself;
	
	public KariAction(yukkurisim_main own)
	{
		super(own);
	}
	
	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	public static synchronized KariAction Get_Instance(yukkurisim_main own)
	{
		if( myself == null)
		{
			myself = new KariAction(own);
		}

		return myself;
	}
	
	@Override
	public void InitDefaultItemTable() {
		/**** デフォルトテーブル定義 ****/
		this.DefaultTable = new HashMap<Integer,Integer>();
		
		DefaultTable.put(0,1);
		DefaultTable.put(1,1);
		DefaultTable.put(2,1);
		DefaultTable.put(3,1);
		DefaultTable.put(4,1);
		DefaultTable.put(5,0);
		DefaultTable.put(6,0);
		DefaultTable.put(7,0);
		DefaultTable.put(8,0);
		DefaultTable.put(9,0);
		DefaultTable.put(10,0);
		DefaultTable.put(11,0);
		DefaultTable.put(12,0);
		DefaultTable.put(13,0);
		DefaultTable.put(14,0);
		DefaultTable.put(15,0);
		DefaultTable.put(16,0);
		DefaultTable.put(17,0);
		DefaultTable.put(18,0);
		DefaultTable.put(19,0);
		DefaultTable.put(20,0);
		DefaultTable.put(21,0);
		DefaultTable.put(22,0);
		DefaultTable.put(23,0);
		DefaultTable.put(24,0);
		DefaultTable.put(25,0);
		DefaultTable.put(26,0);
		DefaultTable.put(27,0);
		DefaultTable.put(28,0);
		DefaultTable.put(29,0);
		DefaultTable.put(30,0);
		DefaultTable.put(31,0);
		DefaultTable.put(32,0);
		DefaultTable.put(33,0);
		DefaultTable.put(34,0);
		DefaultTable.put(35,0);
		DefaultTable.put(36,0);
		DefaultTable.put(37,0);
		DefaultTable.put(38,0);
		DefaultTable.put(39,0);
		DefaultTable.put(40,0);
		DefaultTable.put(41,0);
		DefaultTable.put(42,0);
		DefaultTable.put(43,0);
		DefaultTable.put(44,0);
		DefaultTable.put(45,0);
		DefaultTable.put(46,0);
		DefaultTable.put(47,0);
		DefaultTable.put(48,0);
		DefaultTable.put(49,0);
		DefaultTable.put(50,0);
		DefaultTable.put(51,0);
		DefaultTable.put(52,0);
		DefaultTable.put(53,0);
		DefaultTable.put(54,0);
		DefaultTable.put(55,0);
		DefaultTable.put(56,0);
		DefaultTable.put(57,0);
		DefaultTable.put(58,0);
		DefaultTable.put(59,0);
		DefaultTable.put(60,0);
		DefaultTable.put(61,0);
		DefaultTable.put(62,0);
		DefaultTable.put(63,0);
		DefaultTable.put(64,0);
		DefaultTable.put(65,0);
		DefaultTable.put(66,0);
		DefaultTable.put(67,0);
		DefaultTable.put(68,0);
		DefaultTable.put(69,0);
		DefaultTable.put(70,0);
		DefaultTable.put(71,0);
		DefaultTable.put(72,0);
		DefaultTable.put(73,0);
		DefaultTable.put(74,0);
		DefaultTable.put(75,0);
		DefaultTable.put(76,0);
		DefaultTable.put(77,0);
		DefaultTable.put(78,0);
		DefaultTable.put(79,0);
		DefaultTable.put(80,0);
		DefaultTable.put(81,0);
		DefaultTable.put(82,0);
		DefaultTable.put(83,0);
		DefaultTable.put(84,0);
		DefaultTable.put(85,0);
		DefaultTable.put(86,0);
		DefaultTable.put(87,0);
		DefaultTable.put(88,0);
		DefaultTable.put(89,0);
		DefaultTable.put(90,0);
		DefaultTable.put(91,0);
		DefaultTable.put(92,0);
		DefaultTable.put(93,0);
		DefaultTable.put(94,0);
		DefaultTable.put(95,0);
		DefaultTable.put(96,0);
		DefaultTable.put(97,0);
		DefaultTable.put(98,0);
		DefaultTable.put(99,0);
	}

	@Override
	public void InitUniqueItemTable() {
		this.UniqueTable = new Vector< HashMap<Integer,Integer> >();

		/**** ユニークテーブル１定義 ****/
		HashMap<Integer,Integer> tmp = new HashMap<Integer,Integer>();
		this.UniqueTable.add(tmp);
		tmp.put(0,1);
		tmp.put(1,1);
		tmp.put(2,1);
		tmp.put(3,1);
		tmp.put(4,1);
		tmp.put(5,2);
		tmp.put(6,2);
		tmp.put(7,2);
		tmp.put(8,2);
		tmp.put(9,0);
		tmp.put(10,0);
		tmp.put(11,0);
		tmp.put(12,0);
		tmp.put(13,0);
		tmp.put(14,0);
		tmp.put(15,0);
		tmp.put(16,0);
		tmp.put(17,0);
		tmp.put(18,0);
		tmp.put(19,0);
		tmp.put(20,0);
		tmp.put(21,0);
		tmp.put(22,0);
		tmp.put(23,0);
		tmp.put(24,0);
		tmp.put(25,0);
		tmp.put(26,0);
		tmp.put(27,0);
		tmp.put(28,0);
		tmp.put(29,0);
		tmp.put(30,0);
		tmp.put(31,0);
		tmp.put(32,0);
		tmp.put(33,0);
		tmp.put(34,0);
		tmp.put(35,0);
		tmp.put(36,0);
		tmp.put(37,0);
		tmp.put(38,0);
		tmp.put(39,0);
		tmp.put(40,0);
		tmp.put(41,0);
		tmp.put(42,0);
		tmp.put(43,0);
		tmp.put(44,0);
		tmp.put(45,0);
		tmp.put(46,0);
		tmp.put(47,0);
		tmp.put(48,0);
		tmp.put(49,0);
		tmp.put(50,0);
		tmp.put(51,0);
		tmp.put(52,0);
		tmp.put(53,0);
		tmp.put(54,0);
		tmp.put(55,0);
		tmp.put(56,0);
		tmp.put(57,0);
		tmp.put(58,0);
		tmp.put(59,0);
		tmp.put(60,0);
		tmp.put(61,0);
		tmp.put(62,0);
		tmp.put(63,0);
		tmp.put(64,0);
		tmp.put(65,0);
		tmp.put(66,0);
		tmp.put(67,0);
		tmp.put(68,0);
		tmp.put(69,0);
		tmp.put(70,0);
		tmp.put(71,0);
		tmp.put(72,0);
		tmp.put(73,0);
		tmp.put(74,0);
		tmp.put(75,0);
		tmp.put(76,0);
		tmp.put(77,0);
		tmp.put(78,0);
		tmp.put(79,0);
		tmp.put(80,0);
		tmp.put(81,0);
		tmp.put(82,0);
		tmp.put(83,0);
		tmp.put(84,0);
		tmp.put(85,0);
		tmp.put(86,0);
		tmp.put(87,0);
		tmp.put(88,0);
		tmp.put(89,0);
		tmp.put(90,0);
		tmp.put(91,0);
		tmp.put(92,0);
		tmp.put(93,0);
		tmp.put(94,0);
		tmp.put(95,0);
		tmp.put(96,0);
		tmp.put(97,0);
		tmp.put(98,0);
		tmp.put(99,0);

		/**** ユニークテーブル２定義 ****/
		tmp = new HashMap<Integer,Integer>();
		this.UniqueTable.add(tmp);
		tmp.put(1,3);
		tmp.put(2,3);
		tmp.put(3,3);
		tmp.put(4,3);
		tmp.put(5,3);
		tmp.put(6,3);		
		/**** ユニークテーブル３定義 ****/
		tmp = new HashMap<Integer,Integer>();
		this.UniqueTable.add(tmp);
		tmp.put(1,4);
		tmp.put(2,4);
		tmp.put(3,4);
		tmp.put(4,4);
		tmp.put(5,4);
		tmp.put(6,4);		


	}

	@Override
	public int getIndexLevel(int status) {
		// 基本コレでよいような気がするんだがなあ・・・
		return status/UniqueTable.size();
	}

	@Override
	public void getRisk(yukkuri_base y) {
		Random sai = new Random();
		
		y.setHungry( y.getHungry() + 100 + sai.nextInt(50) );	// ちょっとだけ空腹が回復（つまみぐい？
		y.setSweety( y.getSweety() - 10  - sai.nextInt(40) );	// ちょっとだけ甘さが下がる
		y.setHP( y.getHP() - 20  - sai.nextInt(30));			// ちょっとだけHPが下がる
	}

	@Override
	public void InitGetMessage() {
		GetMessage = "を獲得した！";
	}

}
