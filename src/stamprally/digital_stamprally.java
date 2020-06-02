package stamprally;

import java.util.ArrayList;

public class digital_stamprally {

	static int point = 3; // ラリーポイントの数
	static int max_syu = 0; // 主催者の点数の最大値
	static int max_san = 0; // 参加者の点数の最大値
	static int max_point_syu = 0; // 主催者の最大点の場所
	static int max_point_san = 0; // 参加者の最大点の場所
	static int sankasya_syu; // 主催者が最大点の場所の参加者の点
	static int syusaisya_san; // 参加者が最大点の場所の主催者の点
	static int gap; // 参加者の最大点とsankasya_syuの差
	static int stamp_syusaisya; // スタンプを置いた時の主催者の点
	static int com = combination(point, 2);
	static int[] combi1 = new int[com];
	static int[] combi2 = new int[com];

	public static void main(String[] args) {

		int[] syusaisya = { 10, 2, 1 }; // syusaisyaの点数
		int[] sankasya = { 1, 1, 1 }; // sankasyaの点数
		int[] new_syusaisya = new int[com]; // 主催者の点数組み合わせver
		int[] new_sankasya = new int[com]; // 参加者の点数組み合
		double result_main;
		int[] stamp = new int[point]; // スタンプの点
		int new_stamp_syusaisya1[] = new int[com];
		int new_stamp_sankasya1[] = new int[com];
		int new_stamp_syusaisya2[] = new int[com];
		int new_stamp_sankasya2[] = new int[com];
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		int max_combi = 0;
		int cost;
		// System.out.println("com:" + com);

		int i = 0;

		for (int x = 0; x < point; x++) {// 全ての２ヶ所の組み合わせにおける主催者と参加者の得点を計算

			for (int y = x + 1; y < point; y++) {

				new_syusaisya[i] = syusaisya[x] + syusaisya[y];
				new_sankasya[i] = sankasya[x] + sankasya[y];

				combi1[i] = x;
				combi2[i] = y;

				// System.out.println("combi1:" + combi1[i]);
				// System.out.println("combi2:" + combi2[i]);

				i++;

			}
		}

		for (int j = 0; j < com; j++) { // 参加者の最大利得(A)を調べる

			if (max_san < new_sankasya[j]) {

				list.clear();
				list.add(j);
				max_san = new_sankasya[j];

			} else if (max_san == new_sankasya[j]) {

				list.add(j);

			}
		}

		 //System.out.println("max:" + max_san); 
		// System.out.println("list:" + list); ok

		for (int j = 0; j < com; j++) { // si+tiの最大値の組み合わせを調べる

			if (max_combi < new_syusaisya[j] + new_sankasya[j]) {

				list2.clear();
				list2.add(j);
				max_combi = new_syusaisya[j] + new_sankasya[j];

			} else if (max_combi == new_syusaisya[j] + new_sankasya[j]) {

				list2.add(j);

			}
		}

		 //System.out.println("max_combi:" + max_combi); 
		// System.out.println("list2:" + list2); 
		
		// スタンプの場合分け調べる
		if (new_syusaisya[list2.get(0)] - (max_san - new_sankasya[list2.get(0)]) == new_syusaisya[list.get(0)]) { // si-(A-ti)=B

			System.out.println("patern1");

			for (int z = 0; z < point; z++) {

				stamp[z] = 0;
				System.out.println("stamp:" + stamp[z]);

			}
		} else if (new_syusaisya[list2.get(0)] - (max_san - new_sankasya[list2.get(0)]) > new_syusaisya[list.get(0)]+ 1) { // si-(A-ti)>B+1

			System.out.println("patern2");

			// スタンプの価値の振り分け

			cost = max_san + 1 - new_sankasya[list2.get(0)];

			// System.out.println("cost:" + cost); ok

			int[][] stamp2 = new int[cost + 1][point];// 2ヶ所の場合のみいけるやつ

			for (int s = 0; s <= cost; s++) {

				int t = cost - s;

				// System.out.println("t:" + t);
				// System.out.println("s:" + s);

				for (int j = 0; j < com; j++) {// 1ヶ所目のスタンプのコスト分を計算

					new_stamp_syusaisya1[j] = new_syusaisya[j];
					new_stamp_sankasya1[j] = new_sankasya[j];

					if (combi1[list2.get(0)] == combi1[j]) {

						new_stamp_syusaisya1[j] = new_syusaisya[j] - t;
						new_stamp_sankasya1[j] = new_sankasya[j] + t;

					}
					if (combi1[list2.get(0)] == combi2[j]) {

						new_stamp_syusaisya1[j] = new_syusaisya[j] - t;
						new_stamp_sankasya1[j] = new_sankasya[j] + t;

					}

					// System.out.println("syu:" + new_stamp_syusaisya1[j]); ok
					// System.out.println("san:" + new_stamp_sankasya1[j]); ok
				}

				for (int j = 0; j < com; j++) {// 2ヶ所目のスタンプのコスト分を計算

					new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j];
					new_stamp_sankasya2[j] = new_stamp_sankasya1[j];

					if (combi2[list2.get(0)] == combi1[j]) {

						new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s;
						new_stamp_sankasya2[j] = new_stamp_sankasya1[j] + s;

					}
					if (combi2[list2.get(0)] == combi2[j]) {

						new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s;
						new_stamp_sankasya2[j] = new_stamp_sankasya1[j] + s;

					}

					// System.out.println("syu2:" + new_stamp_syusaisya2[j]); ok
					// System.out.println("san2:" + new_stamp_sankasya2[j]); ok

				}

				int new_max_san = 0;

				// kitaiti
				for (int x = 0; x < com; x++) {

					if (new_max_san < new_stamp_sankasya2[x]) {

						list3.clear();
						list3.add(x);
						new_max_san = new_stamp_sankasya2[x];

					} else if (new_max_san == new_stamp_sankasya2[x]) {

						list3.add(x);

					}
				}

				int sum = 0;

				for (int k = 0; k < list3.size(); k++) {

					sum += new_stamp_syusaisya2[list3.get(k)];

				}

				// System.out.println("sum:" + sum);

				result_main = (double) sum / (double) list3.size();

				System.out.println("result2:" + result_main);// ok

				if (new_syusaisya[list.get(0)] < result_main) {

					stamp[combi1[list2.get(0)]] = t;
					stamp[combi2[list2.get(0)]] = s;

					for (int v = 0; v < point; v++) {

						System.out.println("stamp:" + stamp[v]);

					}

					System.out.println(" ");
				} 
			}
		}else if (new_syusaisya[list2.get(0)]- (max_san - new_sankasya[list2.get(0)]) > new_syusaisya[list.get(0)]) { // si-(A-ti)>B

			System.out.println("patern3");

			// スタンプの価値の振り分け

			cost = max_san - new_sankasya[list2.get(0)];

			// System.out.println("cost:" + cost); ok

			// int[][] stamp2 = new int[cost + 1][point];// 2ヶ所の場合のみいけるやつ

			for (int s1 = 0; s1 <= cost; s1++) {

				int t1 = cost - s1;

				// System.out.println("t:" + t);
				// System.out.println("s:" + s);

				for (int j = 0; j < com; j++) {// 1ヶ所目のスタンプのコスト分を計算

					new_stamp_syusaisya1[j] = new_syusaisya[j];
					new_stamp_sankasya1[j] = new_sankasya[j];

					if (combi1[list2.get(0)] == combi1[j]) {

						new_stamp_syusaisya1[j] = new_syusaisya[j] - t1;
						new_stamp_sankasya1[j] = new_sankasya[j] + t1;

					}
					if (combi1[list2.get(0)] == combi2[j]) {

						new_stamp_syusaisya1[j] = new_syusaisya[j] - t1;
						new_stamp_sankasya1[j] = new_sankasya[j] + t1;

					}

					// System.out.println("syu:" + new_stamp_syusaisya1[j]); ok
					// System.out.println("san:" + new_stamp_sankasya1[j]); ok
				}

				for (int j = 0; j < com; j++) {// 2ヶ所目のスタンプのコスト分を計算

					new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j];
					new_stamp_sankasya2[j] = new_stamp_sankasya1[j];

					if (combi2[list2.get(0)] == combi1[j]) {

						new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s1;
						new_stamp_sankasya2[j] = new_stamp_sankasya1[j] + s1;

					}
					if (combi2[list2.get(0)] == combi2[j]) {

						new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s1;
						new_stamp_sankasya2[j] = new_stamp_sankasya1[j] + s1;

					}

					// System.out.println("syu2:" + new_stamp_syusaisya2[j]); ok
					// System.out.println("san2:" + new_stamp_sankasya2[j]); ok

				}

				int new_max_san1 = 0;

				// kitaiti
				for (int x = 0; x < com; x++) {

					if (new_max_san1 < new_stamp_sankasya2[x]) {

						list3.clear();
						list3.add(x);
						new_max_san1 = new_stamp_sankasya2[x];

					} else if (new_max_san1 == new_stamp_sankasya2[x]) {

						list3.add(x);

					}
				}

				int sum1 = 0;

				for (int k = 0; k < list3.size(); k++) {

					sum1 += new_stamp_syusaisya2[list3.get(k)];

				}

				// System.out.println("sum:" + sum);

				result_main = (double) sum1 / (double) list3.size();

				System.out.println("result2:" + result_main);// ok

				if (new_syusaisya[list.get(0)] < result_main) {

					stamp[combi1[list2.get(0)]] = t1;
					stamp[combi2[list2.get(0)]] = s1;

					for (int v = 0; v < point; v++) {

						System.out.println("stamp:" + stamp[v]);

					}

					System.out.println(" ");

				}

			}
		}
	}

	/*
	 * 
	 * 
	 * 
	 * result_main = kitai(new_syusaisya, new_sankasya); //
	 * System.out.println("result:" + result_main);ok
	 * 
	 * if (result_main == 0.0) {
	 * 
	 * for (int z = 0; z < point; z++) {
	 * 
	 * stamp[z] = 0; System.out.println("stamp:" + stamp[z]); }
	 * 
	 * } else {
	 * 
	 * if (syusaisya_san >= result_main) {
	 * 
	 * for (int z = 0; z < point; z++) {
	 * 
	 * stamp[z] = 0; System.out.println("stamp:" + stamp[z]);
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * // System.out.println("max:" + combi1[max_point_syu]);
	 * 
	 * int[][] stamp2 = new int[gap + 1][point];// 2ヶ所の場合のみいけるやつ
	 * 
	 * for (int s = 0; s <= gap; s++) {
	 * 
	 * int t = gap - s;
	 * 
	 * // System.out.println("t:" + t); // System.out.println("s:" + s);
	 * 
	 * for (int j = 0; j < com; j++) {// 1ヶ所目のスタンプのコスト分を計算
	 * 
	 * new_stamp_syusaisya1[j] = new_syusaisya[j]; new_stamp_sankasya1[j] =
	 * new_sankasya[j];
	 * 
	 * if (combi1[max_point_syu] == combi1[j]) {
	 * 
	 * new_stamp_syusaisya1[j] = new_syusaisya[j] - t; new_stamp_sankasya1[j] =
	 * new_sankasya[j] + t;
	 * 
	 * } if (combi1[max_point_syu] == combi2[j]) {
	 * 
	 * new_stamp_syusaisya1[j] = new_syusaisya[j] - t; new_stamp_sankasya1[j] =
	 * new_sankasya[j] + t;
	 * 
	 * }
	 * 
	 * // System.out.println("syu:" + new_stamp_syusaisya1[j]); //
	 * System.out.println("san:" + new_stamp_sankasya1[j]); }
	 * 
	 * for (int j = 0; j < com; j++) {// 2ヶ所目のスタンプのコスト分を計算
	 * 
	 * new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j]; new_stamp_sankasya2[j] =
	 * new_stamp_sankasya1[j];
	 * 
	 * if (combi2[max_point_syu] == combi1[j]) {
	 * 
	 * new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s; new_stamp_sankasya2[j]
	 * = new_stamp_sankasya1[j] + s;
	 * 
	 * } if (combi2[max_point_syu] == combi2[j]) {
	 * 
	 * new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s; new_stamp_sankasya2[j]
	 * = new_stamp_sankasya1[j] + s;
	 * 
	 * }
	 * 
	 * // System.out.println("syu2:" + new_stamp_syusaisya2[j]); //
	 * System.out.println("san2:" + new_stamp_sankasya2[j]);
	 * 
	 * }
	 * 
	 * int new_max_san = 0;
	 * 
	 * // kitaiti for (int x = 0; x < com; x++) {
	 * 
	 * if (new_max_san < new_stamp_sankasya2[x]) {
	 * 
	 * list.clear(); list.add(x); new_max_san = new_stamp_sankasya2[x];
	 * 
	 * } else if (new_max_san == new_stamp_sankasya2[x]) {
	 * 
	 * list.add(x);
	 * 
	 * } }
	 * 
	 * int sum = 0;
	 * 
	 * for (int k = 0; k < list.size(); k++) {
	 * 
	 * sum += new_stamp_syusaisya2[list.get(k)];
	 * 
	 * }
	 * 
	 * // System.out.println("sum:" + sum);
	 * 
	 * result_main = (double) sum / (double) list.size();
	 * 
	 * //System.out.println("result2:" + result_main);// ok
	 * 
	 * 
	 * if (syusaisya_san < result_main) {
	 * 
	 * stamp[combi1[max_point_syu]] = t; stamp[combi2[max_point_syu]] = s;
	 * 
	 * for (int v = 0; v < point; v++) {
	 * 
	 * System.out.println("stamp:" + stamp[v]);
	 * 
	 * }
	 * 
	 * System.out.println(" ");
	 * 
	 * } else {
	 * 
	 * for (int z = 0; z < point; z++) {
	 * 
	 * stamp[z] = 0; // System.out.println("stamp:" + stamp[z]);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } } }
	 */

	public static double kitai(int st[], int st2[]) {

		double result;
		ArrayList<Integer> list_syu = new ArrayList<Integer>(); // syusaisyaの最大値の場所
		ArrayList<Integer> list_san = new ArrayList<Integer>(); // sankasyaの最大値の場所

		for (int x = 0; x < com; x++) {// 主催者の最大点の組み合わせとその点数を調べる

			if (max_syu < st[x]) {

				list_syu.clear();
				list_syu.add(x);
				// max_point_syu = x; // 主催者の最大値の場所
				max_syu = st[x]; // 主催者の最大値

			} else if (max_syu == st[x]) {

				list_syu.add(x);

			}

		}
		for (int y = 0; y < com; y++) {// 参加者の最大点の組み合わせとその点数を調べる

			if (max_san < st2[y]) {

				list_san.clear();
				list_san.add(y);
				max_san = st2[y]; // 参加者の最大値

			} else if (max_san == st2[y]) {

				list_san.add(y);

			}
		}

		int count = 0;

		for (int i = 0; i < list_syu.size(); i++) {

			for (int j = 0; j < list_san.size(); j++) {

				if (list_syu.get(i) == list_san.get(j)) {

					count++;

				}
			}
		}
		if (count != 0) { // syusaisyaと参加者の最大値が一緒だった場合

			result = 0.0;

		} else {

			sankasya_syu = st2[list_syu.get(0)]; // syusaisyaの最大点の場所の参加者の点

			// System.out.println("sankasya_syu:"+sankasya_syu+ "");

			gap = max_san - sankasya_syu;

			// System.out.println("gap:"+gap+ "");

			int sum = 0;

			for (int k = 0; k < list_san.size(); k++) {

				sum += st[list_san.get(k)];// 参加者の最大点の場所の主催者の点

			}

			syusaisya_san = sum;

			// System.out.println("syusaisya_san:" + syusaisya_san + "");ok

			stamp_syusaisya = max_syu - gap;

			result = (double) (stamp_syusaisya + syusaisya_san) / (list_san.size() + 1);

		}

		return result;

	}

	public static int combination(int m, int n) {

		int com;
		int kaijo1 = 1;
		int kaijo2 = 1;

		if (n == m || n == 0) {

			com = 1;

		} else if (n == 1) {

			com = m;

		} else {

			for (int a = 1; a <= n; a++) {

				kaijo1 *= a;

			}
			for (int b = m; b >= m - n + 1; b--) {

				kaijo2 *= b;

			}

			com = kaijo2 / kaijo1;

		}

		return com;

	}

}
