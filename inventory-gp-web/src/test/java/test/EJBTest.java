package test;


public class EJBTest {

	/**
	 * @param args
	 *//*
	public static void main(String[] args) {

		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			env.put(Context.PROVIDER_URL, "t3://ceweblogicdes.embraconnet.com.br:7001");
			Context ctx = new InitialContext(env);
			System.out.println("Initial Context created");
			PessoaRemote mpb = (PessoaRemote) ctx.lookup("ejb:ihoda-ejb-ear/ihoda-ejb/ejb.ihoda.PessoaBean#br.com.embracon.ihoda.ejb.bean.remote.PessoaRemote");
			
			try {
				mpb.findByCPF("34002896803");
			} catch (PessoaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("End");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}*/

}
