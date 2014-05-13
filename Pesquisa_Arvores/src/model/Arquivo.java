package model;

import java.io.*;

public abstract class Arquivo {

	public static void EscreverArquivo(String pNomeArquivo, String pTexto)
			throws IOException {

		File arquivo = new File(pNomeArquivo);
		boolean existe = arquivo.exists();

		if (!existe)
			arquivo.createNewFile();

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(pTexto);
		bw.newLine();

		bw.close();
		fw.close();
	}

	public static void AlterarArquivo(String pNomeArquivo, String pTextoAnt,
			String pTexto) throws IOException {

		String arquivo = pNomeArquivo;
		String arquivoTmp = pNomeArquivo + "temp";

		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTmp));
		BufferedReader rw = new BufferedReader(new FileReader(arquivo));

		String linha;
		while ((linha = rw.readLine()) != null) {
			if (linha.contains(pTextoAnt)) {
				linha = linha.replace(pTextoAnt, pTexto);
			}
			bw.write(linha);
			bw.newLine();
		}

		bw.close();
		rw.close();

		new File(arquivo).delete();
		new File(arquivoTmp).renameTo(new File(arquivo));

	}

	public static Arq LeArquivo(String pNomeArquivo) throws IOException {
		Arq arq = new Arq();

		File arquivo = new File(pNomeArquivo);
		boolean existe = arquivo.exists();

		if (!existe)
			arquivo.createNewFile();

		FileReader fr = new FileReader(arquivo);
		BufferedReader br = new BufferedReader(fr);

		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(
				arquivo));
		linhaLeitura.skip(arquivo.length());
		arq.numeroLinhas = linhaLeitura.getLineNumber();
		if (arq.numeroLinhas == 0)
			arq.numeroLinhas += 1;
		linhaLeitura.close();

		arq.texto = new String[arq.numeroLinhas];

		int i = 0;
		while (br.ready()) {
			String linha = br.readLine();
			arq.texto[i] = linha;
			i++;
		}

		if (i == 0)
			arq.texto[0] = "";

		br.close();
		fr.close();

		return arq;

	}
}