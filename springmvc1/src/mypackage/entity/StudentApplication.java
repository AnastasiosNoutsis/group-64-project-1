package mypackage.entity;

import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_application")
public class StudentApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_id")
	private int app_id;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name="username")
	private Student student;
	
	@Column(name="onoma_patros")
	private String onoma_patros;
	
	@Column(name="onoma_mitros")
	private String onoma_mitros;
	
	@Column(name="pateras_ergazetai")
	private int pateras_ergazetai;
	
	@Column(name="mitera_ergazetai")
	private int mitera_ergazetai;
	
	@Column(name="oikogeneiako_eisodima")
	private int oikogeneiako_eisodima;
	
	@Column(name="adelfia_spoudazoun")
	private int adelfia_spoudazoun;
	
	@Column(name="entopiotita")
	private int entopiotita;
	
	@Column(name="address_epik")
	private String address_epik;
	
	@Column(name="til_epik")
	private String til_epik;
	
	@Column(name="email_epik")
	private String email_epik;
	
	@Column(name = "eggrisi")
	private int eggrisi;
	
	@Column(name="seira")
	private int seira;
	
	@Column(name="vathmos")
	private int vathmos;
	
	@Column(name="result")
	private int result;
		

	public StudentApplication() {
		
	}


	public StudentApplication(int app_id, Student student, String onoma_patros, String onoma_mitros,
			int pateras_ergazetai, int mitera_ergazetai, int oikogeneiako_eisodima, int adelfia_spoudazoun,
			int entopiotita, String address_epik, String til_epik, String email_epik, int eggrisi, int seira,
			int vathmos, int result) {
		this.app_id = app_id;
		this.student = student;
		this.onoma_patros = onoma_patros;
		this.onoma_mitros = onoma_mitros;
		this.pateras_ergazetai = pateras_ergazetai;
		this.mitera_ergazetai = mitera_ergazetai;
		this.oikogeneiako_eisodima = oikogeneiako_eisodima;
		this.adelfia_spoudazoun = adelfia_spoudazoun;
		this.entopiotita = entopiotita;
		this.address_epik = address_epik;
		this.til_epik = til_epik;
		this.email_epik = email_epik;
		this.eggrisi = eggrisi;
		this.seira = seira;
		this.vathmos = vathmos;
		this.result = result;
	}
	
	
	public int getApp_id() {
		return app_id;
	}


	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public String getOnoma_patros() {
		return onoma_patros;
	}


	public void setOnoma_patros(String onoma_patros) {
		this.onoma_patros = onoma_patros;
	}


	public String getOnoma_mitros() {
		return onoma_mitros;
	}


	public void setOnoma_mitros(String onoma_mitros) {
		this.onoma_mitros = onoma_mitros;
	}


	public int getPateras_ergazetai() {
		return pateras_ergazetai;
	}


	public void setPateras_ergazetai(int pateras_ergazetai) {
		this.pateras_ergazetai = pateras_ergazetai;
	}


	public int getMitera_ergazetai() {
		return mitera_ergazetai;
	}


	public void setMitera_ergazetai(int mitera_ergazetai) {
		this.mitera_ergazetai = mitera_ergazetai;
	}


	public int getOikogeneiako_eisodima() {
		return oikogeneiako_eisodima;
	}


	public void setOikogeneiako_eisodima(int oikogeneiako_eisodima) {
		this.oikogeneiako_eisodima = oikogeneiako_eisodima;
	}


	public int getAdelfia_spoudazoun() {
		return adelfia_spoudazoun;
	}


	public void setAdelfia_spoudazoun(int adelfia_spoudazoun) {
		this.adelfia_spoudazoun = adelfia_spoudazoun;
	}


	public int getEntopiotita() {
		return entopiotita;
	}


	public void setEntopiotita(int entopiotita) {
		this.entopiotita = entopiotita;
	}


	public String getAddress_epik() {
		return address_epik;
	}


	public void setAddress_epik(String address_epik) {
		this.address_epik = address_epik;
	}


	public String getTil_epik() {
		return til_epik;
	}


	public void setTil_epik(String til_epik) {
		this.til_epik = til_epik;
	}


	public String getEmail_epik() {
		return email_epik;
	}


	public void setEmail_epik(String email_epik) {
		this.email_epik = email_epik;
	}

	
	public int getEggrisi() {
		return eggrisi;
	}


	public void setEggrisi(int eggrisi) {
		this.eggrisi = eggrisi;
		if (this.eggrisi==1) {
			calculate_vathmos();
		} else {
			this.vathmos = -1;
		}
		
	}


	public int getSeira() {
		return seira;
	}


	public void setSeira(int seira) {
		this.seira = seira;
	}


	public int getVathmos() {
		return vathmos;
	}


	public void setVathmos(int vathmos) {
		this.vathmos = vathmos;
	}


	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}
	
	public void calculate_vathmos() {
		int points = 0;
		
		if (this.oikogeneiako_eisodima==0 && this.pateras_ergazetai==0 && this.mitera_ergazetai==0) {
			points = 999;
		} else {
			if (this.oikogeneiako_eisodima<10000) {
				points = 100;
			} else {
				if (this.oikogeneiako_eisodima<15000) {
					points = 30;
				}
			}
		}
		
		points = points + this.adelfia_spoudazoun*20;
		
		if (this.entopiotita==0) {
			points = points + 50;	
		}

		this.vathmos = points;
	}


	@Override
	public String toString() {
		return "StudentApplication [app_id=" + app_id + ", student=" + student + ", onoma_patros=" + onoma_patros
				+ ", onoma_mitros=" + onoma_mitros + ", pateras_ergazetai=" + pateras_ergazetai + ", mitera_ergazetai="
				+ mitera_ergazetai + ", oikogeneiako_eisodima=" + oikogeneiako_eisodima + ", adelfia_spoudazoun="
				+ adelfia_spoudazoun + ", entopiotita=" + entopiotita + ", address_epik=" + address_epik + ", til_epik="
				+ til_epik + ", email_epik=" + email_epik + ", eggrisi=" + eggrisi + ", seira=" + seira + ", vathmos="
				+ vathmos + ", result=" + result + "]";
	}


	
	public static Comparator<StudentApplication> StuAppVathmos = new Comparator<StudentApplication>() {

		public int compare(StudentApplication s1, StudentApplication s2) {

		   int vathmos1 = s1.getVathmos();
		   int vathmos2 = s2.getVathmos();

		   /*For descending order*/
		   return vathmos2-vathmos1;
	   }};
	
	
	

}
