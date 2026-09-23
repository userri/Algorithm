
import java.util.*;
import java.io.*;

class Solution {
	// 0.5칸 단위 충돌 처리하기 위해 좌표 2배
	static int[][] map = new int[4001][4001];

	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Atom {
		int x, y, dir, power;
		boolean isDead;

		public Atom(int x, int y, int dir, int power) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
			isDead = false;
		}

	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			List<Atom> atoms = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int dir = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				atoms.add(new Atom(x, y, dir, power));
			}

			int sum = 0;

			// x = 0 -> x = 4000까지 최대 4000번 이동가능
			for (int step = 0; step < 4000; step++) {
				if (atoms.isEmpty())
					break;

				// 일단 이동 후 map에 power 합치기
				for (Atom atom : atoms) {
					atom.x += dx[atom.dir];
					atom.y += dy[atom.dir];
					if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000) {
						atom.isDead = true;
						continue;
					}
					map[atom.x][atom.y] += atom.power;
				}

				// 충돌한 애들 에너지를 정답에 더해주고 새로운 atom 리스트 저장
				List<Atom> nextAtoms = new ArrayList<>();
				for (Atom atom : atoms) {
					if (atom.isDead)
						continue;
					if (map[atom.x][atom.y] > atom.power) {
						sum += atom.power;
					} else {
						nextAtoms.add(atom);
					}
				}

				// 사용한 map칸 되돌리기
				for (Atom atom : atoms) {
					if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000)
						continue;
					
					map[atom.x][atom.y] = 0;
				}

				atoms = nextAtoms;
			}

			sb.append("#" + test_case + " ");
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

}