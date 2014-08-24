package com.ttProject.quercus.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.caucho.quercus.Quercus;
import com.caucho.quercus.QuercusDieException;
import com.caucho.quercus.QuercusErrorException;
import com.caucho.quercus.QuercusExitException;
import com.caucho.quercus.env.Env;
import com.caucho.quercus.page.QuercusPage;
import com.caucho.vfs.Path;
import com.caucho.vfs.StdoutStream;
import com.caucho.vfs.WriteStream;

/**
 * quercusの動作テスト
 * @author taktod
 */
public class QuercusTest {
	/** ロガー */
	private Logger logger = Logger.getLogger(QuercusTest.class);
	@Test
	public void test() throws Exception {
		logger.info("テストスタート");
		ArgManager.getInstance().setArg("test", new TestClass("test"));
		QuercusEx ex = new QuercusEx();
		ex.execute("test.php", "message");
	}
	private static class QuercusEx extends Quercus {
		private Env env;
		private String arg = "";
		public QuercusEx() {
			super();
			init();
			start();
		}
		public Env getEnv() {
			return env;
		}
		public void execute(String path, String arg) throws IOException {
			setFileName(path);
			this.arg = arg;
			execute();
		}
		public void execute(Path path) throws IOException {
			QuercusPage page = parse(path);
			WriteStream os = new WriteStream(StdoutStream.create());

			os.setNewlineString("\n");
			os.setEncoding("UTF-8");

			env = this.createEnv(page, os, null, null);
			env.setGlobalValue("_JAVAARG", objectToValue(arg));
			env.start();

			try {
				env.execute();
			} catch (QuercusDieException e) {
				e.printStackTrace();
			} catch (QuercusExitException e) {
				e.printStackTrace();
			} catch (QuercusErrorException e) {
				e.printStackTrace();
			} finally {
				os.flush();
				env.close();
			}
		}
	}
}
