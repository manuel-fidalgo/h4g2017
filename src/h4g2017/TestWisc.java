package h4g2017;
public class TestWisc {
	String directo[][];
	String inverso[][];
	
	int index;
	int index_binario;

	public TestWisc(){
		
		index = index_binario = 0;
		
		directo= new String[7][2];
		directo[0][0]="386";		directo[0][1]="612";
		directo[1][0]="3417";		directo[1][1]="6158";
		directo[2][0]="84239";		directo[2][1]="52186";
		directo[3][0]="389174";		directo[3][1]="796483";
		directo[4][0]="5174238";	directo[4][1]="9852163";
		directo[5][0]="16459763";	directo[5][1]="29763154";
		directo[6][0]="538712469";	directo[6][1]="426917835";



		inverso = new String[7][2];

		inverso[0][0]="25";			inverso[0][1]="63";
		inverso[1][0]="574";		inverso[1][1]="259";
		inverso[2][0]="7296";		inverso[2][1]="8493";
		inverso[3][0]="41357";		inverso[3][1]="97852";
		inverso[4][0]="165298";		inverso[4][1]="367194";
		inverso[5][0]="8592342";	inverso[5][1]="4579281";
		inverso[6][0]="69163258";	inverso[6][1]="31795482";

		inverso = inversor(inverso);

	}
	
	public String[][] inversor(String inverso[][]){

		String [][]invertidos= new String[7][2];

		for (int i = 0; i < invertidos.length; i++) {
			for (int j = 0; j < invertidos[i].length; j++) {
				invertidos[i][j]=invierte(inverso[i][j]);
			}
		}

		return invertidos;
	}

	public String invierte(String aInvertir){
		String invertido="";
		for (int i = aInvertir.length()-1; i>=0;i--) {
			invertido+=aInvertir.charAt(i);
		}
		return invertido;

	}
	
	public String getNext(){
		String s;
		s = directo[index][index_binario];
		if(index_binario==1){
			index_binario=0;
			index++;
		}else{
			index_binario++;
		}
		
		return s;
	}
}
