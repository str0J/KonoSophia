package outils;

public class Backup {
		int notetp=0;
		int noteexam=0;
		int matiereid;
		static int tppourcent=0;
		static int exampourcent=0;
		public static String Etudiant;
		public static String EtudiantID;
		public static float moyenne(int exam,int tp) {
			return(((exam*exampourcent)+(tp*tppourcent))/100);
		}
}
