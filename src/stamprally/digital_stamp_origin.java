package stamprally;

import java.util.ArrayList;

public class digital_stamp_origin {

	public static void main(String[] args) {

		int[] syusaisya = { 3, 2, 1 }; // ��Î҂̓_��
		int[] sankasya = { 1, 2, 3 }; // �Q���҂̓_��
		int point = 3; // �����[�|�C���g�̐�
		int[] combi1 = new int[point];
		int[] combi2 = new int[point];
		int[] new_syusaisya = new int[point]; // ��Î҂̓_���g�ݍ��킹ver
		int[] new_sankasya = new int[point]; // �Q���҂̓_���g�ݍ��킹ver
		int max_syu = 0; // ��Î҂̓_���̍ő�l
		int max_san = 0; // �Q���҂̓_���̍ő�l
		int max_point_syu = 0;
		int max_point_san = 0;
		int[] stamp = new int[point]; // �X�^���v�̓_
		int sankasya_syu;
		int syusaisya_san;
		int gap;
		int stamp_syusaisya;
		double result;
		int new_stamp_syusaisya1[] = new int[point];
		int new_stamp_sankasya1[] = new int[point];
		int new_stamp_syusaisya2[] = new int[point];
		int new_stamp_sankasya2[] = new int[point];
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

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

		for (int x = 0; x < point; x++) {// ��Î҂̍ő�_�̑g�ݍ��킹�Ƃ��̓_���𒲂ׂ�

			if (max_syu < new_syusaisya[x]) {

				max_point_syu = x; // ��Î҂̍ő�l�̏ꏊ
				max_syu = new_syusaisya[x]; // ��Î҂̍ő�l

			}

		}
		for (int y = 0; y < point; y++) {// �Q���҂̍ő�_�̑g�ݍ��킹�Ƃ��̓_���𒲂ׂ�

			if (max_san < new_sankasya[y]) {

				max_point_san = y; // �Q���҂̍ő�l�̏ꏊ
				max_san = new_sankasya[y]; // �Q���҂̍ő�l

			}
		}

		if (max_point_syu == max_point_san) { // ��Î҂ƎQ���҂̍ő�l���ꏏ�������ꍇ

			for (int z = 0; z < point; z++) {

				stamp[z] = 0;

				System.out.println("stamp:" + stamp[z]);

			}

		} else {

			sankasya_syu = new_sankasya[max_point_syu]; // ��Î҂̍ő�_�̏ꏊ�̎Q���҂̓_

			gap = max_san - sankasya_syu;

			// Log.d(TAG,"gap:"+gap+ "");

			syusaisya_san = new_syusaisya[max_point_san]; // �Q���҂̍ő�_�̏ꏊ�̎�Î҂̓_

			stamp_syusaisya = max_syu - gap;

			result = (double) (stamp_syusaisya + syusaisya_san) / 2;

			// System.out.println("result:" + result);

			if (syusaisya_san >= result) {

				for (int z = 0; z < point; z++) {

					stamp[z] = 0;
					System.out.println("stamp:" + stamp[z]);
				}

			} else {

				// System.out.println("max:" + combi1[max_point_syu]);

				int[][] stamp2 = new int[gap + 1][point];

				for (int s = 0; s <= gap; s++) {

					int t = gap - s;

					for (int j = 0; j < point; j++) {

						new_stamp_syusaisya1[j] = new_syusaisya[j];
						new_stamp_sankasya1[j] = new_sankasya[j];

						if (combi1[max_point_syu] == combi1[j]) {

							new_stamp_syusaisya1[j] = new_syusaisya[j] - t;
							new_stamp_sankasya1[j] = new_sankasya[j] + t;

						}
						if (combi1[max_point_syu] == combi2[j]) {

							new_stamp_syusaisya1[j] = new_syusaisya[j] - t;
							new_stamp_sankasya1[j] = new_sankasya[j] + t;

						}

						// System.out.println("syu:" + new_stamp_syusaisya1[j]);
						// System.out.println("san:" + new_stamp_sankasya1[j]);
					}

					int new_max_san = 0;

					// kitaiti
					for (int x = 0; x < point; x++) {// ��Î҂̍ő�_�̑g�ݍ��킹�Ƃ��̓_���𒲂ׂ�

						if (new_max_san < new_stamp_sankasya1[x]) {

							list.clear();
							list.add(x);
							new_max_san = new_stamp_sankasya1[x]; // ��Î҂̍ő�l

						} else if (new_max_san == new_stamp_sankasya1[x]) {

							list.add(x);

						}
					}

					int sum = 0;

					for (int k = 0; k < list.size(); k++) {

						sum += new_stamp_syusaisya1[list.get(k)];

					}

					// System.out.println("sum"+sum);

					result = (double) sum / (double) list.size(); // ok

					// System.out.println("result:" + result);

					if (syusaisya_san >= result) {

						for (int z = 0; z < point; z++) {

							stamp[z] = 0;
						}

					} else {

						for (int z = 0; z < point; z++) {

							stamp[z] = 0;
						}

						stamp[combi1[max_point_syu]] = t; // ok

					}

					/*
					 * for (int p = 0; p < point; p++) {
					 * 
					 * System.out.println("stamp:" + stamp[p]); }
					 */

					for (int j = 0; j < point; j++) {

						new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j];
						new_stamp_sankasya2[j] = new_stamp_sankasya1[j];

						if (combi2[max_point_syu] == combi1[j]) {

							new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s;
							new_stamp_sankasya2[j] = new_stamp_sankasya1[j] + s;

						}
						if (combi2[max_point_syu] == combi2[j]) {

							new_stamp_syusaisya2[j] = new_stamp_syusaisya1[j] - s;
							new_stamp_sankasya2[j] = new_stamp_sankasya1[j] + s;

						}

						// System.out.println("syu2:" + new_stamp_syusaisya2[j]);
						// System.out.println("san2:" + new_stamp_sankasya2[j]);

					}

					int new_max_san2 = 0;

					// kitaiti
					for (int x = 0; x < point; x++) {// ��Î҂̍ő�_�̑g�ݍ��킹�Ƃ��̓_���𒲂ׂ�

						if (new_max_san2 < new_stamp_sankasya2[x]) {

							list2.clear();
							list2.add(x);
							new_max_san2 = new_stamp_sankasya2[x]; // ��Î҂̍ő�l

						} else if (new_max_san2 == new_stamp_sankasya2[x]) {

							list2.add(x);

						}
					}

					int sum2 = 0;

					for (int k = 0; k < list2.size(); k++) {

						sum2 += new_stamp_syusaisya2[list2.get(k)];

					}

					// System.out.println("sum2:" + sum2);

					result = (double) sum2 / (double) list2.size();

					// System.out.println("result2:" + result);//ok

					if (syusaisya_san < result) {

						stamp[combi2[max_point_syu]] = s;

					}

					int sum3 = 0;

					for (int p = 0; p < point; p++) {

						sum3 += stamp[p];

					}

					if (sum3 != 0) {

						for (int p = 0; p < point; p++) {

							System.out.println("stamp:" + stamp[p]);

						}

					}

				}

			}

		}

	}
}