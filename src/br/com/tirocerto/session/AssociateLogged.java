package br.com.tirocerto.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.profile.AssociateProfile;

@Component
@SessionScoped
public class AssociateLogged implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Associate associate;

	public AssociateLogged() {

	}
	
	public void login(Associate associate) {
		this.associate = associate;
	}
	
	public void logout() {
		this.associate = null;
	}
	
	public Associate getAssociate() {
		return associate;
	}

	public AssociateProfile getAssociateProfile() {
		AssociateProfile associateProfile = new AssociateProfile();
		
		if (this.associate != null) {
			associateProfile.setLoggedIn(true);// como o usuario corrente nao eh
												// nulo, significa que ele esta
												// logado
			// o nivel de acesso vc obtem de onde quiser (banco de dados,
			// arquivo properties etc)
			// caso nao utilize niveis de acesso no seu controle de acessos, nao
			// precisa informar o accessLevel
			// perfil.setAccessLevel(0);
			// Especificamente no seu caso, cada usuario possui apenas um role.
			// Entao, faca simplesmente isso:
			List<String> roles = new ArrayList<String>();
			// roles.add(this.getUsuario().getRole());
			associateProfile.setRoles(roles);
		}
		
		return associateProfile;
	}
}
