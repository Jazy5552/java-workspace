import java.awt.event.KeyEvent;


public class stringToKeyEvents {
	
	//26 letters
	int[] Keys = {KeyEvent.VK_A,KeyEvent.VK_B,KeyEvent.VK_C,KeyEvent.VK_D,KeyEvent.VK_E,KeyEvent.VK_F,KeyEvent.VK_G,KeyEvent.VK_H,KeyEvent.VK_I,KeyEvent.VK_J,KeyEvent.VK_K,KeyEvent.VK_L,KeyEvent.VK_M,KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_P,KeyEvent.VK_Q,KeyEvent.VK_R,KeyEvent.VK_S,KeyEvent.VK_T,KeyEvent.VK_U,KeyEvent.VK_V,KeyEvent.VK_W,KeyEvent.VK_X,KeyEvent.VK_Y,KeyEvent.VK_Z,
			KeyEvent.VK_SPACE,KeyEvent.VK_COMMA,KeyEvent.VK_PERIOD,KeyEvent.VK_CLOSE_BRACKET,KeyEvent.VK_OPEN_BRACKET,KeyEvent.VK_MINUS,KeyEvent.VK_SEMICOLON,
			KeyEvent.VK_0,KeyEvent.VK_1,KeyEvent.VK_2,KeyEvent.VK_3,KeyEvent.VK_4,KeyEvent.VK_5,KeyEvent.VK_6,KeyEvent.VK_7,KeyEvent.VK_8,KeyEvent.VK_9,KeyEvent.VK_0};
	char[] KeyCon = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			' ',',','.',']','[','-',':',
			'1','2','3','4','5','6','7','8','9','0'};
	
	public int[] toKeyEvents(String st){
		st = st.toLowerCase();
		char[] ch = st.toCharArray();
		int[] ret = new int[ch.length];
		boolean pass;
		
		for (int i=0;i<ch.length;i++){
			pass=false;
			for (int x=0;x<KeyCon.length;x++){
				if (KeyCon[x]==ch[i]){
					ret[i] = Keys[x];
					pass=true;
				}
			}
			if (!pass){
				ret[i] = KeyEvent.VK_SPACE;
			}
			
		}
		
		System.out.println(ret);
		return ret;
		
	}
}
