// 채점용 코드(건들지말것)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int CMD_INIT = 100;
	private static final int CMD_ENTER = 200;
	private static final int CMD_PULL_OUT = 300;
	private static final int CMD_SEARCH = 400;

	private static UserSolution usersolution = new UserSolution();

	public static class RESULT_E {
		int success;
		String locname;

		RESULT_E() {
			success = -1;
		}
	}

	public static class RESULT_S {
		int cnt;
		String[] carlist = new String[5];

		RESULT_S() {
			cnt = -1;
		}
	}

	private static boolean run(BufferedReader br) throws Exception {
		int Q, N, M, L;
		int mTime;

		String mCarNo;
		String mStr;

		int ret = -1, ans;

		RESULT_E res_e;
		RESULT_S res_s;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Q = Integer.parseInt(st.nextToken());

		boolean okay = false;

		for (int q = 0; q < Q; ++q) {
			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				L = Integer.parseInt(st.nextToken());
				usersolution.init(N, M, L);
				okay = true;
				break;
			case CMD_ENTER:
				mTime = Integer.parseInt(st.nextToken());
				mCarNo = st.nextToken();
				res_e = usersolution.enter(mTime, mCarNo);
				ans = Integer.parseInt(st.nextToken());
				if (res_e.success != ans) {
					System.out.println("E@" + q + " " + mCarNo + " got=" + res_e.success + " want=" + ans);
					okay = false;
				}
				if (ans == 1) {
					mStr = st.nextToken();
					if (res_e.success != ans) {
						System.out.println("E@" + q + " " + mCarNo + " got=" + res_e.success + " want=" + ans);
						okay = false;
					}
				}
				break;
			case CMD_PULL_OUT:
				mTime = Integer.parseInt(st.nextToken());
				mCarNo = st.nextToken();
				ret = usersolution.pullout(mTime, mCarNo);
				ans = Integer.parseInt(st.nextToken());
				if (ret != ans) {
					System.out.println("P@" + q + " " + mCarNo + " got=" + ret + " want=" + ans);
					okay = false;
				}
				break;
			case CMD_SEARCH:
				mTime = Integer.parseInt(st.nextToken());
				mStr = st.nextToken();
				res_s = usersolution.search(mTime, mStr);
				ans = Integer.parseInt(st.nextToken());
				if (res_s.cnt != ans) {
					System.out.println("S@" + q + " got=" + res_s.cnt + " want=" + ans);
					okay = false;
				}
				for (int i = 0; i < ans; ++i) {
					mCarNo = st.nextToken() + mStr;
					if (!mCarNo.equals(res_s.carlist[i]))
						okay = false;
				}
				break;
			default:
				okay = false;
				break;
			}
		}

		return okay;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int TC = Integer.parseInt(st.nextToken());
		int MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}

}
