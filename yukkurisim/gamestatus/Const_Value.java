package gamestatus;

public class Const_Value {
	// 定数管理クラス
	public final	int	SCREEN_WIDTH = 800;		//画面幅
	public final	int 画面幅 = SCREEN_WIDTH;
	
	public final	int	SCREEN_HEIGHT = 600;	//画面高さ
	public final	int 画面高さ = SCREEN_HEIGHT;
	
	public final	int SCREEN_CELL_WIDTH = 13;				//画面横セル幅
	public final	int 画面横セル幅 = SCREEN_CELL_WIDTH;	//画面横セル幅
	
	public final	int SCREEN_CELL_HEIGHT = 26;			//画面縦セル幅
	public final	int 画面縦セル幅 = SCREEN_CELL_HEIGHT;	//画面縦セル幅

	public final	int MAPCHIP_WIDTH	=	102;				//マップチップの横幅
	public final	int マップチップ横幅 = MAPCHIP_WIDTH;
	public final	int MAPCHIP_HEIGHT	=56;
	public final	int マップチップ縦幅 = MAPCHIP_HEIGHT;
	
	public final	int MAX_STATUS_VALUE	=	100;		//協調性等のステータス値のMAX
	public final	int C_ステータス最大値	=	MAX_STATUS_VALUE;
	public final	int MAX_HUNGRY_VALUE	=	10000;		//満腹度のMAX
	public final	int C_おなか最大値		=	MAX_HUNGRY_VALUE;
	
	public final	int	C_START_CASH_VALUE	=	1000;		//所持金の初期値
	public final	int 所持金初期値		=	C_START_CASH_VALUE;
	
	public final	int	STATE_MOVING = 1;
	public final	int 移動中 = STATE_MOVING;
	public final	int STATE_WAITING = 2;
	public final	int 待機中 = STATE_WAITING;
	public final	int STATE_EATING = 3;
	public final	int 食事中 = STATE_EATING;
	public final	int STATE_FARMING = 4;
	public final	int 農業中 = STATE_FARMING;
	public final	int STATE_FARMED = 5;
	public final	int 農業後待機中 = STATE_FARMED;
	public final	int STATE_HANTED = 6;
	public final	int 狩り後待機中 = STATE_HANTED;

	//****************死亡系状態(state>=100で死亡判定)
	public final	int STATE_DEAD = 100;
	public final	int 死亡 = STATE_DEAD;
	public final	int STATE_DEAD_DESEASE = 101;
	public final	int 病死 = STATE_DEAD_DESEASE;

	//****************タイプの定義　ここから
		public final int	TYPE_NULL						=	-999;	//	使用不可
		public final int	TYPE_未使用						=	TYPE_NULL;
	
		public final int	TYPE_ALL_DENY					=	0	;	//	すべてが可能
		public final int	TYPE_PASS_DENY					=	1	;	//	通行のみ可能
		public final int	TYPE_ACTION_DENY				=	2	;	//	干渉のみ可能
		public final int	TYPE_FLYING_ONLY_PASS_DENY		=	3	;	//	飛行ユニットのみ通行可能
		public final int	TYPE_FLYING_ONLY_ACTION_DENY	=	4	;	//	飛行ユニットのみ干渉可能
		public final int	TYPE_FLYING_ONLY_ALL_DENY		=	5	;	//	飛行ユニットのみすべてが可能

		// マップチップタイプ日本語定数定義
		public final int	TYPE_すべてが可能	=	TYPE_ALL_DENY	;
		public final int	TYPE_通行のみ可能	=	TYPE_PASS_DENY	;
		public final int	TYPE_干渉のみ可能	=	TYPE_ACTION_DENY	;
		public final int	TYPE_飛行ユニットのみ通行可能	=	TYPE_FLYING_ONLY_PASS_DENY	;
		public final int	TYPE_飛行ユニットのみ干渉可能	=	TYPE_FLYING_ONLY_ACTION_DENY	;
		public final int	TYPE_飛行ユニットのみすべてが可能	=	TYPE_FLYING_ONLY_ALL_DENY	;
	
		// キャラクター系
		public final int C_TYPE_COUNT		=	7;							// キャラクター系タイプが全部で何種類
		public final int TYPE_キャラクター系タイプ総数 = C_TYPE_COUNT;	// 定義されているか（増やしたら更新すること）
		

		//****************キャラクター系オブジェクト番号の定義　ここから		
		public final int C_TYPE_YUKKURI	=	100;
		public final int TYPE_ゆっくりobject	=	C_TYPE_YUKKURI;
		public final int C_TYPE_MARISA	=	101;
		public final int TYPE_まりさobject		=	C_TYPE_MARISA;

		//****************建築物系オブジェクト番号の定義　ここから
		public final int C_TYPE_TREE		=	120;
		public final int C_TYPE_HOUSE		=	121;
		public final int C_TYPE_ENTRY		=	122;
		public final int C_TYPE_ROCK		=	123;
		public final int C_TYPE_KAKINE	=	124;
		public final int C_TYPE_FARM		=	125;
		public final int TYPE_木object			=	C_TYPE_TREE;
		public final int TYPE_家object			=	C_TYPE_HOUSE;
		public final int TYPE_入り口object		=	C_TYPE_ENTRY;
		public final int TYPE_岩object			=	C_TYPE_ROCK;
		public final int TYPE_垣根object			=	C_TYPE_KAKINE;
		public final int TYPE_農地object			=	C_TYPE_FARM;

		public final int TYPE_object_キャラクタと建築物番号敷居値 =120;
	//****************シーン番号の定義　ここから
		public final int SCENE_TITLE	=	1;		//タイトル
		public final int SCENE_GAME01 =	2;		//ゲーム１
		public final int SCENE_LOAD	=	3;		//ロードシーン
		public final int SCENE_LOADING=	10;		//ローディング表示
		public final int SCENE_TEST	=	10000;	//テストシーン
		public final int SCENE_TEST_02=	10001;	//テストシーン02
	
	//****************シーン番号の定義　ここまで
		
		public final int C_MOJI_THRESHOLD =		2;
		public final int メッセージテキスト速度 = C_MOJI_THRESHOLD;
		
		public final int C_SCROLLING_MAX_X 	=	200;
		public final int スクロール最大範囲X 	= 	C_SCROLLING_MAX_X;
		public final int C_SCROLLING_MAX_Y 	=	200;
		public final int スクロール最大範囲Y 	= 	C_SCROLLING_MAX_Y;
		public final int C_SCROLLING_MIN_X 	=	-200;
		public final int スクロール最小範囲X 	= 	C_SCROLLING_MIN_X;
		public final int C_SCROLLING_MIN_Y 	=	-200;
		public final int スクロール最小範囲Y 	= 	C_SCROLLING_MIN_Y;
		public final int C_SCROLLING_PLAY		=	10;					// スクロール開始となる画面端の「遊び」
		public final int スクロール遊び		=	C_SCROLLING_PLAY;	// 「遊び」って英語だと「play」なんだねー、へぇー
																		// カーソルがぁっ！ 画面端ぃっ！

		//****************サブウインドウ・ボタン番号の定義　ここから
		public final int BUTTON_ID_BUILD_NULL	=	0;
		public final int ボタンID_ヌル		=	BUTTON_ID_BUILD_NULL;
		public final int BUTTON_ID_BUILD_TREE	=	10;
		public final int ボタンID_木ボタン	=	BUTTON_ID_BUILD_TREE;
		public final int BUTTON_ID_BUILD_HOUSE=	11;
		public final int ボタンID_家ボタン	=	BUTTON_ID_BUILD_HOUSE;
		public final int BUTTON_ID_BUILD_FARM=	12;
		public final int ボタンID_農地ボタン	=	BUTTON_ID_BUILD_FARM;
		
		public final int BUTTON_ID_BUILD_REMOVE =	999;
		public final int ボタンID_撤去ボタン=	BUTTON_ID_BUILD_REMOVE;
		public final int BUTTON_ID_BUILD_EXIT =	9999;
		public final int ボタンID_閉じるボタン=	BUTTON_ID_BUILD_EXIT;
		
		public final int BUTTON_ID_DIALOG_OK	=	500;
		public final int ボタンID_ダイアログOK=	BUTTON_ID_DIALOG_OK;
		public final int BUTTON_ID_DIALOG_YES	=	501;
		public final int ボタンID_ダイアログYES=	BUTTON_ID_DIALOG_YES;
		public final int BUTTON_ID_DIALOG_NO	=	502;
		public final int ボタンID_ダイアログNO=	BUTTON_ID_DIALOG_NO;
	
		public final int BUTTON_ID_ITEM_01	=	600;	// アイテムボタン１
		public final int ボタンID_アイテム01	=	BUTTON_ID_ITEM_01;
		public final int BUTTON_ID_ITEM_02	=	601;	// アイテムボタン２
		public final int ボタンID_アイテム02	=	BUTTON_ID_ITEM_02;
		public final int BUTTON_ID_ITEM_03	=	602;	// アイテムボタン３
		public final int ボタンID_アイテム03	=	BUTTON_ID_ITEM_03;
		public final int BUTTON_ID_ITEM_04	=	603;	// アイテムボタン４
		public final int ボタンID_アイテム04	=	BUTTON_ID_ITEM_04;
		public final int BUTTON_ID_ITEM_05	=	604;	// アイテムボタン５
		public final int ボタンID_アイテム05	=	BUTTON_ID_ITEM_05;
		public final int BUTTON_ID_ITEM_06	=	605;	// アイテムボタン６
		public final int ボタンID_アイテム06	=	BUTTON_ID_ITEM_06;
		
		public final int BUTTON_ID_TITLE_01	=	700;
		public final int ボタンID_タイトル01	=	BUTTON_ID_TITLE_01;
		public final int BUTTON_ID_TITLE_02	=	701;
		public final int ボタンID_タイトル02	=	BUTTON_ID_TITLE_02;
		
		//****************サブウインドウ・ラベル番号の定義　ここから
		public final int LABEL_ID_BUILD_COST = 900;
		public final int ラベルID_建築_費用=LABEL_ID_BUILD_COST;
		public final int LABEL_ID_BUILD_CASH = 901;
		public final int ラベルID_建築_所持金=LABEL_ID_BUILD_CASH;
		
		public final int LABEL_ID_ACTION_SYUZOKU = 1000;
		public final int ラベルID_アクション_種族名 = LABEL_ID_ACTION_SYUZOKU;
		public final int LABEL_ID_ACTION_NOUGYOU = 1001;
		public final int ラベルID_アクション_農業 = LABEL_ID_ACTION_NOUGYOU;
		public final int LABEL_ID_ACTION_KARI = 1002;
		public final int ラベルID_アクション_狩り = LABEL_ID_ACTION_KARI;
		public final int LABEL_ID_ACTION_CHECK_KARI = 1003;
		public final int ラベルID_アクション_説明_狩り = LABEL_ID_ACTION_CHECK_KARI;
		public final int LABEL_ID_ACTION_CHECK_NOUGYOU = 1004;
		public final int ラベルID_アクション_説明_農業 = LABEL_ID_ACTION_CHECK_NOUGYOU;
		public final int LABEL_ID_ACTION_CHECK_ASOBI = 1005;
		public final int ラベルID_アクション_説明_遊び = LABEL_ID_ACTION_CHECK_ASOBI;

		public final int LABEL_ID_STATUS_SYUZOKU = 1100;
		public final int ラベルID_ステータス_種族 =LABEL_ID_STATUS_SYUZOKU;
		public final int LABEL_ID_STATUS_TAIRYOKU = 1101;
		public final int ラベルID_ステータス_体力 = LABEL_ID_STATUS_TAIRYOKU;
		public final int LABEL_ID_STATUS_KOKORO	= 1102;
		public final int ラベルID_ステータス_こころ = LABEL_ID_STATUS_KOKORO;
		public final int LABEL_ID_STATUS_TIKARA = 1103;
		public final int ラベルID_ステータス_ちから = LABEL_ID_STATUS_TIKARA;
		public final int LABEL_ID_STATUS_AMASA = 1104;
		public final int ラベルID_ステータス_甘さ = LABEL_ID_STATUS_TIKARA;
		public final int LABEL_ID_STATUS_ONAKA = 1105;
		public final int ラベルID_ステータス_おなか = LABEL_ID_STATUS_ONAKA;
		public final int LABEL_ID_STATUS_DAYS = 1106;
		public final int ラベルID_ステータス_日数 = LABEL_ID_STATUS_DAYS;

		//****************サブウインドウ・その他の定義　ここから
		public final int OTHER_ID_ACTION_IMAGE = 1200;
		public final int その他ID_画像表示 = OTHER_ID_ACTION_IMAGE;
		public final int CHECKBOX_ID			=	1300;
		public final int チェックボックスid	=	CHECKBOX_ID;

		//****************育成パートに受け渡す種族別アクション定義　ここから
		public final int ACTION_KARI = 1;
		public final int アクション_狩り = ACTION_KARI;
		public final int ACTION_FARM = 2;
		public final int アクション_農業 = ACTION_FARM;
		public final int ACTION_PLAY = 3;
		public final int アクション_遊ぶ = ACTION_PLAY;
		
		

		//****************カーソルを使用する際の戻り処理区分　ここから
		public final int COMEBACK_CURSOR_BUILD_01_TREE		=	0;
		public final int カーソル処理_ビルドウインドウ01_木	=	0;
		public final int COMEBACK_CURSOR_BUILD_01_HOUSE		=	1;
		public final int カーソル処理_ビルドウインドウ01_家	=	1;
		public final int COMEBACK_CURSOR_BUILD_01_FARM		=	2;
		public final int カーソル処理_ビルドウインドウ01_農地	=	2;
		
		
		//****************タイマ関連　ここから
		public final int 一日の長さ			=		200;				// 育成パートの長さ
		public final int TIME_COUNT_OF_DAY	=		一日の長さ;			// 育成パートの長さ
		public final int パート_施策_建築区分	=		0;
		public final int SISAKU_BUILD_KBN		=		パート_施策_建築区分;
		public final int パート_施策_行動区分	=		1;
		public final int SISAKU_ACTION_KBN	=		パート_施策_行動区分;
		public final int パート_育成区分		=		2;
		public final int IKUSEI_KBN			=		パート_育成区分;
		public final int パート_襲撃区分		=		3;
		public final int SYUGEKI_KBN			=		パート_襲撃区分;
		
		//****************アイテム関連　ここから
		public final int アイテム最大番号		=		13;
		// アイテム番号0は予備用、何かに使うかもしれないし、使わないかもしれない
		public final int アイテム_穀物袋 = 1;        //
		public final int アイテム_ゆっくりフード = 2;        //
		public final int アイテム_ポン・デ・おやつ = 3;        //
		public final int アイテム_シュークリーム = 4;        //
		public final int アイテム_オレンジジュース = 5;        //
		public final int アイテム_花火 = 6;        //
		public final int アイテム_えほん = 7;        //
		public final int アイテム_おんみょうだま = 8;        //
		public final int アイテム_ほうき = 9;        //
		public final int アイテム_ハサミ = 10;        //
		public final int アイテム_ブラシ = 11;        //
		public final int アイテム_れみりゃぶくろ = 12;        //
		public final int アイテム_要石のかけら = 13;        //

		//****************画像番号定義　ここから
		public final int 画像番号_ゆっくりれいむ歩く = 31000;
		public final int 画像番号_ゆっくりれいむ待つ = 31001;
		public final int 画像番号_ゆっくりれいむ食べる = 31002;
		public final int 画像番号_ゆっくりれいむ病死 = 31003;
		public final int 画像番号_ゆっくりれいむ死亡 = 31004;
		public final int 画像番号_ゆっくりれいむ歩く_反転 = 31005;
		public final int 画像番号_ゆっくりれいむ待つ_反転 = 31006;
		public final int 画像番号_ゆっくりれいむ食べる_反転 = 31007;
		public final int 画像番号_ゆっくりれいむ病死_反転 = 31008;
		public final int 画像番号_ゆっくりれいむ死亡_反転 = 31009;
			
		public final int 画像番号_カーソル = 31100;
		public final int 画像番号_家1 = 31110;
		public final int 画像番号_家2 = 31111;
		public final int 画像番号_家3 = 31112;
		public final int 画像番号_家4 = 31113;
		public final int 画像番号_家5 = 31114;
		public final int 画像番号_家6 = 31115;
		public final int 画像番号_タイル0 = 31120;
		public final int 画像番号_草原1 = 31121;
		public final int 画像番号_草原2 = 31122;
		public final int 画像番号_川1 = 31123;
		public final int 画像番号_川2 = 31124;
		public final int 画像番号_エントランス = 31125;
		public final int 画像番号_木 = 31150;
		public final int 画像番号_農地 = 31151;
		//************************ Widget系ここから ***********************************//
		public final int 画像番号_チェックボックスオン = 31200;
		public final int 画像番号_チェックボックスオフ = 31201;
		public final int 画像番号_ダイアログウィンドウ = 31202;
		public final int 画像番号_メッセージウィンドウ = 31203;
		public final int 画像番号_タイマ = 31204;
		public final int 画像番号_メニュ = 31205;
		public final int 画像番号_チェックボタン = 31206;
		public final int 画像番号_ショップボタン = 31207;
		public final int 画像番号_トラップボタン = 31208;
		public final int 画像番号_ビルドボタン = 31209;
		public final int 画像番号_クラッシュボタン = 31210;
		public final int 画像番号_データボタン = 31211;
		public final int 画像番号_クイックボタン = 31212;
		public final int 画像番号_パニッシュボタン = 31213;
		public final int 画像番号_ラブボタン = 31214;
		public final int 画像番号_アイテムボタン = 31215;
		public final int 画像番号_ビルドウインドウ = 31230;
		public final int 画像番号_木ボタン = 31231;
		public final int 画像番号_家ボタン = 31232;
		public final int 画像番号_閉じるボタン = 31233;
		public final int 画像番号_撤去ボタン = 31234;
		public final int 画像番号_農地ボタン = 31235;
		public final int 画像番号_アクションウインドウ = 31250;
		public final int 画像番号_ステータスウインドウ = 31270;
		public final int 画像番号_アイテムウインドウ = 31300;
		public final int 画像番号_ダイアログOK = 31320;
		//************************ アイテム系ここから ***********************************//
		public final int 画像番号_アイテムハテナ = 31340;
		
		
		
		//************************ ローディング画面処理区分 *****************************//
		public final int ローディング_ロジック1 = 1;
}