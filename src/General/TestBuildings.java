package General;

import java.util.*;

class TestBuildings {

	public static void main(String[] args) {
		TestBuildings t = new TestBuildings();
		//int H[] = { 1 }; // 1
		//int H[] = { 1, 2 }; // 3
		int H[] = { 3, 1, 4 }; // 10
		//int H[] = { 5, 3, 2, 4 }; // 17
		//int H[] = { 5, 3, 5, 2, 1 }; // 19
		//int H[] = { 7, 7, 3, 7, 7 }; // 35
		//int H[] = { 1, 1, 7, 6, 6, 6 }; // 30
		//int H[] = { 10, 4, 4, 10, 4, 10, 4, 10, 10 }; 
		
		System.out.println(t.solution(H));
	}
	
	/*public int solution02(int[] H) {
		//return area of atmost 2 banners
		if (H.length == 1) {
			return H[0];
		} else if (H.length == 2) {
			return H[0] + H[1];
		}
		
		//1 banner
		//maximum height * number of buildings
		//single_banner = max(H) * len(H)
		int singleBanner = max(H) * H.length;
		int smallestArea = singleBanner;

		//2 banner
		//for i in range(1, len(H)):
		for (int i = 1; i < H.length; i++) {
			//double_banner = (max(H[0:i]) * len(H[0:i])) + (max(H[i:]) * len(H[i:]))
			doubleBanner = (max(H[i]) * len(H[i])) + (max(H[i]) * len(H[i]));
			
			//if double_banner < smallest_area:
				smallestArea = doubleBanner;
				
				
			
		}

		return smallestArea;
	}
	
	public int max2(int ... indexes) {
		return 0;
	}
	
	public int max(int[] H) {
		int big = 0;
		for (int i = 0; i < H.length; i++) {
			if (H[i] > big) {
				big = H[i];
			}
		}
		return big;
	}*/
	
	public void calcArea(int[] H, int min, int max) {
		
		
		
		
		
	}

	public int solution(int[] H) {

		List<Item> possibilidades = new ArrayList<Item>();
		int totalIndices = 0, sumAreas = 0;
		
		if (H.length == 1) {
			return H[0];
		} else if (H.length == 2) {
			return H[0] + H[1];
		}

		for (int i = 0; i < H.length; i++) {
			List<Integer> listI = new ArrayList<Integer>();
			listI.add(i);
			Item item = new Item(H[i], listI);
			possibilidades.add(item);
			totalIndices += i;
		}
		
		calcArea(H, 0, H.length - 1);
		
		
		
		
		
		
		
		
		
		for (int i = 0; i < H.length; i++) {
			for (int j = i + 1; j < H.length; j++) {

				int height = 0;
				int width = 0;
				int area = 0;

				for (int x = i; x <= j; x++) {
					if (height == 0) {
						height = H[x];
					} else if (height < H[x]) {
						height = H[x];
					}
				}

				width = (j + 1) - (i);

				area = height * width;
				System.out.println("i=" + i + " j="+ j + " height=" + height+ " width="+width+" area=" + area);

				List<Integer> listI = new ArrayList<Integer>();
				for (int k = i; k <= j; k++) {
					listI.add(k);
				}
				Item item = new Item(area, listI);
				possibilidades.add(item);
			}
		}

		System.out.println(possibilidades.toString());
        
        for (int i = 0; i < possibilidades.size(); i++) {
            for (int j = i + 1; j < possibilidades.size(); j++) {
                if (possibilidades.get(i).sumItems() + possibilidades.get(j).sumItems() == totalIndices) {
                    
                    if (sumAreas == 0) {
                        sumAreas = possibilidades.get(i).getIndex() + possibilidades.get(j).getIndex();
                    } else if (sumAreas > possibilidades.get(i).getIndex() + possibilidades.get(j).getIndex()) {
                    	if ((possibilidades.get(i).getNumberItems() + possibilidades.get(j).getNumberItems()) == H.length) {
                    		sumAreas = possibilidades.get(i).getIndex() + possibilidades.get(j).getIndex();
                    		System.out.println("i="+i + " >> "+ possibilidades.get(i).getIndex() + " j="+j + " " + possibilidades.get(j).getIndex());
                    	}
                    }
                }
            }
        }

		return sumAreas;

	}
}

class Item {
	int index;
	List<Integer> items = new ArrayList<Integer>();

	public Item(int index, List<Integer> items) {
		this.index = index;
		this.items = items;
	}

	public int getIndex() {
		return this.index;
	}

	public List<Integer> getItems() {
		return this.items;
	}

	public String toString() {
		String index = "" + this.index;
		String items = "";

		for (Integer i : this.items) {
			items += " " + i;
		}

		return index + " " + items;
	}

	public int sumItems() {
		int sum = 0;

		for (Integer i : this.getItems()) {
			sum += i;
		}

		return sum;
	}
	
	public int getNumberItems() {
        return this.getItems().size();
    }
}
