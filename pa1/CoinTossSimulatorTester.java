package pa1;

public class CoinTossSimulatorTester {
	public static void main(String[] args){
		int i = 0;
		CoinTossSimulator coin = new CoinTossSimulator();
		
		
		coin.run(0);
		System.out.println("----After constructor:----");
		System.out.println("Number of traials[exp:0]: "+coin.getNumTrials());
		System.out.println("TwoHeads tosses: "+coin.getTwoHeads());
		System.out.println("TwoTrails tosses: "+coin.getTwoTails());
		System.out.println("HeadTails tosses: "+coin.getHeadTails());
		
		if(coin.getNumTrials() == coin.getTwoHeads()+coin.getTwoTails()+coin.getHeadTails()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
		//coin.reset();
		
		System.out.println("--------After run(1)------");
		coin.run(1);
		System.out.println("NumTrails[exp:1]: "+coin.getNumTrials());
		System.out.println("TwoHeads: "+coin.getTwoHeads());
		System.out.println("TwoTrails: "+coin.getTwoTails());
		System.out.println("HeadTails: "+coin.getHeadTails());
		
		if(coin.getNumTrials() == coin.getTwoHeads()+coin.getTwoTails()+coin.getHeadTails()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
		//coin.reset();
		
		System.out.println("--------After run(10)------");
		coin.run(10);
		System.out.println("NumTrails[exp:11]: "+coin.getNumTrials());
		System.out.println("TwoHeads: "+coin.getTwoHeads());
		System.out.println("TwoTrails: "+coin.getTwoTails());
		System.out.println("HeadTails: "+coin.getHeadTails());

		
		if(coin.getNumTrials() == coin.getTwoHeads()+coin.getTwoTails()+coin.getHeadTails()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
		//coin.reset();
		
		System.out.println("--------After run(100)-----");
		coin.run(100);
		System.out.println("NumTrails[exp:111]: "+coin.getNumTrials());
		System.out.println("TwoHeads: "+coin.getTwoHeads());
		System.out.println("TwoTrails: "+coin.getTwoTails());
		System.out.println("HeadTails: "+coin.getHeadTails());

		
		if(coin.getNumTrials() == coin.getTwoHeads()+coin.getTwoTails()+coin.getHeadTails()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
		coin.reset();
		
		System.out.println("--------After reset--------");
		coin.run(0);
		System.out.println("NumTrails[exp:0]: "+coin.getNumTrials());
		System.out.println("TwoHeads: "+coin.getTwoHeads());
		System.out.println("TwoTrails: "+coin.getTwoTails());
		System.out.println("HeadTails: "+coin.getHeadTails());
		
		if(coin.getNumTrials() == coin.getTwoHeads()+coin.getTwoTails()+coin.getHeadTails()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
		coin.reset();
		
		System.out.println("------After run(1000)------");
		coin.run(1000);
		System.out.println("NumTrails[exp:1000]: "+coin.getNumTrials());
		System.out.println("TwoHeads: "+coin.getTwoHeads());
		System.out.println("TwoTrails: "+coin.getTwoTails());
		System.out.println("HeadTails: "+coin.getHeadTails());
		
		if(coin.getNumTrials() == coin.getTwoHeads()+coin.getTwoTails()+coin.getHeadTails()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
		coin.reset();
		
	}

}
