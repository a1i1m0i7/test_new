package stamprally;

import java.util.ArrayList;

public class digital_stamprally {

	static int point = 3; // �����[�|�C���g�̐�
	static int max_syu = 0; // ��Î҂̓_���̍ő�l
	static int max_san = 0; // �Q���҂̓_���̍ő�l
	static int max_point_syu = 0; // ��Î҂̍ő�_�̏ꏊ
	static int max_point_san = 0; // �Q���҂̍ő�_�̏ꏊ
	static int sankasya_syu; // ��Î҂��ő�_�̏ꏊ�̎Q���҂̓_
	static int syusaisya_san; // �Q���҂��ő�_�̏ꏊ�̎�Î҂̓_
	static int gap; // �Q���҂̍ő�_��sankasya_syu�̍�
	static int stamp_syusaisya; // �X�^���v��u�������̎�Î҂̓_
	static int com = combination(point, 2);
	static int[] combi1 = new int[com];
	static int[] combi2 = new int[com];

	public static void main(String[] args) {

		int[] syusaisya = { 10, 2, 1 }; // syusaisya�̓_��
		int[] sankasya = { 1, 1, 1 }; // sankasya�̓_��
		int[] new_syusaisya = new int[com]; // ��Î҂̓_���g�ݍ��킹ver
		int[] new_sankasya = new int[com]; // �Q���҂̓_���g�ݍ�
		double result_main;
		int[] stamp = new int[point]; // �X�^���v�̓_
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

		for (int x = 0; x < point; x++) {// �S�Ă̂Q�����̑g�ݍ��킹�ɂ������Î҂ƎQ���҂̓��_���v�Z

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

		for (int j = 0; j < com; j++) { // �Q���҂̍ő嗘��(A)�𒲂ׂ�

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

		for (int j = 0; j < com; j++) { // si+ti�̍ő�l�̑g�ݍ��킹�𒲂ׂ�

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
		
		// �X�^���v�̏ꍇ�������ׂ�
		if (new_syusaisya[list2.get(0)] - (max_san - new_sankasya[list2.get(0)]) == new_syusaisya[list.get(0)]) { // si-(A-ti)=B

			System.out.println("patern1");

			for (int z = 0; z < point; z++) {

				stamp[z] = 0;
				System.out.println("stamp:" + stamp[z]);

			}
		} else if (new_syusaisya[list2.get(0)] - (max_san - new_sankasya[list2.get(0)]) > new_syusaisya[list.get(0)]+ 1) { // si-(A-ti)>B+1

			System.out.println("patern2");

			// �X�^���v�̉��l�̐U�蕪��

			cost = max_san + 1 - new_sankasya[list2.get(0)];

			// System.out.println("cost:" + cost); ok

			int[][] stamp2 = new int[cost + 1][point];// 2�����̏ꍇ�݂̂�������

			for (int s = 0; s <= cost; s++) {

				int t = cost - s;

				// System.out.println("t:" + t);
				// System.out.println("s:" + s);

				for (int j = 0; j < com; j++) {// 1�����ڂ̃X�^���v�̃R�X�g�����v�Z

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

				for (int j = 0; j < com; j++) {// 2�����ڂ̃X�^���v�̃R�X�g�����v�Z

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

			// �X�^���v�̉��l�̐U�蕪��

			cost = max_san - new_sankasya[list2.get(0)];

			// System.out.println("cost:" + cost); ok

			// int[][] stamp2 = new int[cost + 1][point];// 2�����̏ꍇ�݂̂�������

			for (int s1 = 0; s1 <= cost; s1++) {

				int t1 = cost - s1;

				// System.out.println("t:" + t);
				// System.out.println("s:" + s);

				for (int j = 0; j < com; j++) {// 1�����ڂ̃X�^���v�̃R�X�g�����v�Z

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

				for (int j = 0; j < com; j++) {// 2�����ڂ̃X�^���v�̃R�X�g�����v�Z

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
	 * int[][] stamp2 = new int[gap + 1][point];// 2�����̏ꍇ�݂̂�������
	 * 
	 * for (int s = 0; s <= gap; s++) {
	 * 
	 * int t = gap - s;
	 * 
	 * // System.out.println("t:" + t); // System.out.println("s:" + s);
	 * 
	 * for (int j = 0; j < com; j++) {// 1�����ڂ̃X�^���v�̃R�X�g�����v�Z
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
	 * for (int j = 0; j < com; j++) {// 2�����ڂ̃X�^���v�̃R�X�g�����v�Z
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
		ArrayList<Integer> list_syu = new ArrayList<Integer>(); // syusaisya�̍ő�l�̏ꏊ
		ArrayList<Integer> list_san = new ArrayList<Integer>(); // sankasya�̍ő�l�̏ꏊ

		for (int x = 0; x < com; x++) {// ��Î҂̍ő�_�̑g�ݍ��킹�Ƃ��̓_���𒲂ׂ�

			if (max_syu < st[x]) {

				list_syu.clear();
				list_syu.add(x);
				// max_point_syu = x; // ��Î҂̍ő�l�̏ꏊ
				max_syu = st[x]; // ��Î҂̍ő�l

			} else if (max_syu == st[x]) {

				list_syu.add(x);

			}

		}
		for (int y = 0; y < com; y++) {// �Q���҂̍ő�_�̑g�ݍ��킹�Ƃ��̓_���𒲂ׂ�

			if (max_san < st2[y]) {

				list_san.clear();
				list_san.add(y);
				max_san = st2[y]; // �Q���҂̍ő�l

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
		if (count != 0) { // syusaisya�ƎQ���҂̍ő�l���ꏏ�������ꍇ

			result = 0.0;

		} else {

			sankasya_syu = st2[list_syu.get(0)]; // syusaisya�̍ő�_�̏ꏊ�̎Q���҂̓_

			// System.out.println("sankasya_syu:"+sankasya_syu+ "");

			gap = max_san - sankasya_syu;

			// System.out.println("gap:"+gap+ "");

			int sum = 0;

			for (int k = 0; k < list_san.size(); k++) {

				sum += st[list_san.get(k)];// �Q���҂̍ő�_�̏ꏊ�̎�Î҂̓_

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
