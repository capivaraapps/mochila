package mochila;

import mochila.Pacote;
import java.util.Scanner;

class Mochila {
    public static void main(String[] args) throws Exception {


    	System.out.println("Insira a quantidade de pacotes");
    	//inserimos a quantidade de pacotes
    	Scanner in = new Scanner(System.in);
    	int numPacotes = in.nextInt();

    	System.out.println("Insira o peso maximo da mochila");
    	//inserimos o peso maximo que pode levar a mochila
    	int pesoMaximo = in.nextInt();
    	
    	Pacote pacotes[] = new Pacote[numPacotes];
    	
    	//geramos N pacotes aleatorios
    	for(int i=0 ; i< numPacotes ; i++){

    		//estabelecemos que o peso maximo de um pacote é a metade do peso maximo que pode levar a mochila
    		int pesoRandom = (int)(Math.random() * pesoMaximo/2 + 1);
    		//estabelecemos um valor maximo de 100 para os pacotes
    		int valorRandom = (int)(Math.random() * 100);
    		
    		Pacote newPacote = new Pacote(valorRandom, pesoRandom);
    		pacotes[i] = newPacote;
    	}
    	
    	/*System.out.println("Valores x Pesos");
    	System.out.println("---------------");
    	for(int i=0 ; i<numPacotes ; i++) {
    		System.out.println(pacotes[i].getValor() + "\t" + pacotes[i].getPeso());
    	}*/
        
        System.out.println("Valor máximo carregavel: " + mochila(pacotes, pesoMaximo));
        
    }
    public static int mochila(Pacote pacotes[], int pesoMaximo) {
    	
    	int N = pacotes.length; // Obtemos o numero total de pacotes.
    	
    	int valores[] = new int[N]; //Criamos arrays independentes para os pesos e os valores
    	int pesos[] = new int[N];
    	
    	for(int i=0 ; i<N ; i++) {
    		valores[i] = pacotes[i].getValor(); //enchemos os arrays independentes com os valores das propriedades peso e valor dos pacotes
    		pesos[i] = pacotes[i].getPeso();
    	}

        int[][] maxValores = new int[N + 1][pesoMaximo + 1]; //Criamos uma matriz. O numero de items está nas filas e os pesos maximos nas colunas +1 a cada lado
        
        //Se a capacidade da mochila é 0 setamos todas as colunas da linha 0 à 0.
        for (int coluna=0 ; coluna<=pesoMaximo ; coluna++) {
        	maxValores[0][coluna] = 0;
        }
        
        //Se não tem nenhum pacote setamos todas as primeiras colunas de cada linha à 0.
        for (int linha=0 ; linha<=N ; linha++) {
        	maxValores[linha][0] = 0;
        }
        
        for (int pacote=1 ; pacote<=N ; pacote++){
            
        	//enchemos os valores linha a linha
            for (int peso=1 ; peso<=pesoMaximo ; peso++){
                
            	//O peso do pacote atual é menor ou igual ao peso maximo?
                if (pesos[pacote-1]<=peso){
                    
                	//Dado um peso, checar se o valor do pacote atual + o valor do pacote que podemos nos permitir com o peso restante
                    //é maior que o valor sem o pacote atual
                	maxValores[pacote][peso]=Math.max(valores[pacote-1]+maxValores[pacote-1][peso-pesos[pacote-1]], maxValores[pacote-1][peso]);
                }
                else {
                    
                	//Se o peso do pacote atual é mais que o peso maximo, seguimos sem o pacote atual
                	maxValores[pacote][peso]=maxValores[pacote-1][peso];
                }
            }
        }
        
        //Printamos a matriz
        /*for (int[] linhas : maxValores) {
        	
            for (int coluna : linhas) {
            	System.out.format("%5d", coluna);
            }
            
            System.out.println();
        }*/
        
        return maxValores[N][pesoMaximo];
    }
}