package gestion_produit_crud.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gestion_produit_crud.config.JwtUtils;
import gestion_produit_crud.config.UtilisateurDetails;
import gestion_produit_crud.dto.LoginRequestDTO;
import gestion_produit_crud.dto.LoginResponseDTO;
import gestion_produit_crud.dto.UtilisateurDTO;
import gestion_produit_crud.dto.UtilisateurUpdateDTO;
import gestion_produit_crud.entity.UtilisateurEntity;
import gestion_produit_crud.mappers.Mappers;
import gestion_produit_crud.repository.UtilisateurRepo;

@Service
public class UtilisateurServiceImp implements UtilisateurService, UserDetailsService {

	@Autowired
	UtilisateurRepo repo;

	@Autowired
	Mappers mappers;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	@org.springframework.context.annotation.Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UtilisateurEntity entity = repo.findByEmail(username);

		if (entity == null) {

			throw new RuntimeException("Utilisateur non trouvé avec l'email : " + username);

		}

		return new UtilisateurDetails(entity);
	}

	@Override
	public UtilisateurDTO inscription(UtilisateurDTO dto) {

		if (repo.findByEmail(dto.getEmail()) != null) {
			throw new RuntimeException("Cet email est déjà utilisé.");
		}

		UtilisateurEntity entity = mappers.UtilisateurDTOEnTotity(dto);

		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		entity.setPassword(encodedPassword);

		UtilisateurEntity saved = repo.save(entity);

		return mappers.UtilisateurEntityToDTO(saved);
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO dto) { // Changez le type de retour ici
		try {
			// 1. Vérification de l'identité
			// Note : utilisez dto.getEmail() si votre champ s'appelle email dans le
			// RequestDTO
			
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

			// 2. Si OK, génération du token
			
			String token = jwtUtils.generateToken(dto.getEmail());

			// 3. Construction de la réponse selon votre schéma LoginResponseDTO
			
			LoginResponseDTO response = new LoginResponseDTO();
			response.setToken(token);
			response.setType("Bearer"); // Correspond à l'exemple de votre schéma

			return response;

		} catch (AuthenticationException e) {
			throw new RuntimeException("Email ou mot de passe incorrect");
		}
	}
	
	

	@Override
	public List<UtilisateurDTO> listeUtilisateur() {
		
		List<UtilisateurEntity> entities = repo.findAll();
		
		List<UtilisateurDTO> dtos = entities.stream()
				.map(mappers::UtilisateurEntityToDTO).toList();
		
		return dtos;
	}

	@Override
	public UtilisateurDTO AfficherUtilisateurParId(Integer id) {
		
		UtilisateurEntity entity = repo.getById(id);
		
		UtilisateurDTO dto = mappers.UtilisateurEntityToDTO(entity);
		
		return dto;
	}

	@Override
	public UtilisateurDTO modifierUtilisateur(UtilisateurUpdateDTO dto, Integer id) {
	    
	    // 1. Récupérer l'utilisateur actuel
	    UtilisateurEntity entity = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));
	    
	    // 2. Vérification de l'email unique
	    if (dto.getEmail() != null && !dto.getEmail().equalsIgnoreCase(entity.getEmail())) {
	        // Si l'email a changé, on vérifie s'il existe déjà en base
	        if (repo.existsByEmail(dto.getEmail())) {
	            throw new RuntimeException("Cet email est déjà utilisé par un autre compte !");
	        }
	        entity.setEmail(dto.getEmail());
	    }

	    // 3. Mise à jour des autres champs
	    if (dto.getNom() != null) {
	        entity.setNom(dto.getNom());
	    }

	    return mappers.UtilisateurEntityToDTO(repo.save(entity));
	}


	@Override
	public void supprimerUtilisateur(Integer id) {
		
		repo.findById(id).orElseThrow(()->new RuntimeException("utilisateur non trouvé !"));
		repo.deleteById(id);
		
	}
	
	

}
