package in.ajay.moneymanager.service;


import in.ajay.moneymanager.dto.CategoryDTO;
import in.ajay.moneymanager.entity.CategoryEntity;
import in.ajay.moneymanager.entity.ProfileEntity;
import in.ajay.moneymanager.repository.CategoryRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ProfileService profileService;
    private final CategoryRepositoy categoryRepositoy;


    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        ProfileEntity profile=profileService.getCurrentProfile();
        if(categoryRepositoy.existsByNameAndProfileId(categoryDTO.getName(),profile.getId())){
          throw new RuntimeException("Category with this name already exist");
        }
        CategoryEntity newCategory=toEntity(categoryDTO,profile);
        newCategory=categoryRepositoy.save(newCategory);
        return toDTO(newCategory);
    }

public List<CategoryDTO> getCategoriesForCurrentUser(){
        ProfileEntity profile=profileService.getCurrentProfile();
        List<CategoryEntity>categories=categoryRepositoy.findByProfileId(profile.getId());
        return categories.stream().map(this::toDTO).toList();
}
public CategoryDTO updateCategory(Long categoryId,CategoryDTO dto){
ProfileEntity profile=profileService.getCurrentProfile();
CategoryEntity existingCategory=categoryRepositoy.findByIdAndProfileId(categoryId,profile.getId())
        .orElseThrow(()->new RuntimeException("Category not found or not accessible"));
existingCategory.setName(dto.getName());
existingCategory.setIcon(dto.getIcon());
categoryRepositoy.save(existingCategory);

return toDTO(existingCategory);
    }
public List<CategoryDTO>getCategoriesByTypeForCurrentUser(String type){
ProfileEntity profile=profileService.getCurrentProfile();
List<CategoryEntity>entities=categoryRepositoy.findByTypeAndProfileId(type,profile.getId());
return entities.stream().map(this::toDTO).toList();
}
    private CategoryEntity toEntity(CategoryDTO categoryDTO, ProfileEntity profile){

        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .profile(profile)
                .type(categoryDTO.getType())

                .build();    }

    private CategoryDTO toDTO(CategoryEntity entity){

        return CategoryDTO.builder()
                .id(entity.getId())
                .profileId(entity.getProfile()!=null? entity.getProfile().getId():null)
                .name(entity.getName())
                .icon(entity.getIcon())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getCreatedAt())
                .type(entity.getType())
                .build();
    }
}
