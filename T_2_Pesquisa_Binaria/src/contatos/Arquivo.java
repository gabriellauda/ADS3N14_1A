package contatos;

import java.io.*;

public abstract class Arquivo {

	public static void EscreverArquivo(String pNomeArquivo, String pTexto)
			throws IOException {

		File file = new File(pNomeArquivo);
		boolean existe = file.exists();

		if (!existe)
			file.createNewFile();

		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(pTexto);
		bw.newLine();

		bw.close();
		fw.close();
	}

	public static void EditFile(String pNomeArquivo, String pTextoAnt,
			String pTexto) throws IOException {

		String file = pNomeArquivo;
		String arquivoTmp = pNomeArquivo + "temp";

		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTmp));
		BufferedReader rw = new BufferedReader(new FileReader(file));

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

		new File(file).delete();
		new File(arquivoTmp).renameTo(new File(file));

	}

	public static Arq ReadFile(String pNomeArquivo) throws IOException {
		Arq arq = new Arq();

		File file = new File(pNomeArquivo);
		boolean existe = file.exists();

		if (!existe)
			file.createNewFile();

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(
				file));
		linhaLeitura.skip(file.length());
		arq.numLinha = linhaLeitura.getLineNumber();
		if (arq.numLinha == 0)
			arq.numLinha += 1;
		linhaLeitura.close();

		arq.text = new String[arq.numLinha];

		int i = 0;
		while (br.ready()) {
			String linha = br.readLine();
			arq.text[i] = linha;
			i++;
		}

		if (i == 0)
			arq.text[0] = "";

		br.close();
		fr.close();

		return arq;

	}
}