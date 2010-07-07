package yukkurisim;

import gamestatus.GameTimer;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 * 農業・ミニゲーム・狩りなど、確率的にアイテムが手に入り何らかの代償を負う
 * 行動のベースクラス
 * @author ポチエル
 *
 */
public abstract class ItemGeneratorActionBase {
	protected int levelIndexMax;	// 入力可能なレベル範囲（ユニークテーブルがいくつ用意されているか？）
	protected int MaxIndex;		// 入力可能なインデックスの最大値

	// テーブルの型は同じ Hashmap<Indexナンバ,アイテムナンバ>
	protected HashMap<Integer,Integer> DefaultTable;				// デフォルトテーブル
	protected Vector< HashMap<Integer,Integer> > UniqueTable;	// ユニークテーブルのベクタ
	protected yukkurisim_main owner;
	/**
	 * コンストラクタ
	 *
	 */
	public ItemGeneratorActionBase(yukkurisim_main own){
		owner = own;
		Initialize();
	}
	
	/**
	 * 初期化メソッド
	 *
	 */
	public void Initialize()
	{
		InitDefaultItemTable();
		InitUniqueItemTable();
	}
	
	/**
	 * アイテムテーブルのディフォルトエリア（ステータスによってテーブルが変動しないエリア）
	 * を初期化する。
	 * この関数が子クラスでオーバーライドされることで、行動ごとのディフルトテーブルを
	 * 定義する。
	 *
	 */
	public abstract void InitDefaultItemTable();
	
	/**
	 * アイテムテーブルのユニークエリア（ステータスによってテーブルが変動するエリア）
	 * を初期化する。
	 * この関数が子クラスでオーバーライドされることで、行動ごとのユニークテーブルを
	 * 定義する。
	 *
	 */
	public abstract void InitUniqueItemTable();
	
	/**
	 * 能力値に応じて適切なユニークテーブルを算出する。
	 * 
	 * @param status
	 * @return
	 */
	public abstract int getIndexLevel(int status);
	
	/**
	 * アイテム取得に挑戦する
	 * 以下の手順で書いたらこのコメントを消すこと
	 * ①yのステータスからgetIndexLevelでインデックス取得
	 * ②確率試行
	 * ③ItemManagerにアクセスし、アイテムの増加処理
	 * ④アナウンス
	 * @param y
	 */
	public void getItemChallenge(yukkuri_base y)
	{
		int UniqueIndex = getIndexLevel(y.Get_Cooperative());
		HashMap<Integer,Integer> UniqueTmp = this.UniqueTable.get(UniqueIndex);
		int MaxTableSize = DefaultTable.size() + UniqueTmp.size();	// デフォルトテーブルのサイズ+選ばれたユニークテーブルのサイズ
		Random sai = new Random();
		int ChallengeIndex = sai.nextInt(MaxTableSize);
		
		if(ChallengeIndex>=DefaultTable.size())
		{	// ユニークテーブルエリアを引いた
			ChallengeIndex -= DefaultTable.size();
			if(UniqueTmp.containsKey(ChallengeIndex))
			{
				int itemNum = UniqueTmp.get(ChallengeIndex);
				if(itemNum!=0)
				{	// ここでアイテム取得確定
					ItemManager iman = ItemManager.Get_Instance(owner);
					iman.setItemIncrement(itemNum);
					Widget_Manager.Get_Instance(owner).Popup_Dialog_Window(iman.getItemName(itemNum)+"を収穫しました！");
					System.out.println("アイテム獲得→"+itemNum);
				}
			}
			else
			{
				// 処理としては何もしない。
			}
		}
		else
		{	// ディフォルトテーブルエリアだった
			if(DefaultTable.containsKey(ChallengeIndex))
			{
				int itemNum = DefaultTable.get(ChallengeIndex);
				if(itemNum!=0)
				{	// ここでアイテム取得確定
					ItemManager iman = ItemManager.Get_Instance(owner);
					iman.setItemIncrement(itemNum);
					Widget_Manager.Get_Instance(owner).Popup_Dialog_Window(iman.getItemName(itemNum)+"を収穫しました！");
					System.out.println("アイテム獲得→"+itemNum);
				}
			}
			else
			{
				// 処理としては何もしない。
			}
			
		}
		getRisk(y);	// 収穫の成否に関係なくリスクは存在する
		System.out.println("item直後タイマ動作→"+GameTimer.Get_Instance(owner, 0).Get_Times());

	}
	
	/**
	 * リスクを取得する。（getItemChallengeからコールしても良い）
	 * @param y
	 */
	public abstract void getRisk(yukkuri_base y);
}
