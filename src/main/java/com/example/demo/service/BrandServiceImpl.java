package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.MyException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.BrandDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.BrandForm;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import javax.persistence.Converter;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class BrandServiceImpl implements BrandService {
    @Autowired
   private BrandRepository brandRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<BrandDto> search(String name, int page , int size)  {
//        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Page<Brand> brands =  brandRepository.findByNameContaining(name,PageRequest.of(page, size, Sort.by("createdDate").descending()));

        Page<BrandDto> brandDtos = brands.map(new Function<Brand, BrandDto>() {
            @Override
            public BrandDto apply(Brand brand) {
                BrandDto brandDto = new BrandDto();
                brandDto.setId(brand.getId());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = formatter.format(brand.getCreatedDate());
                brandDto.setCreatedDate(strDate);
                brandDto.setName(brand.getName());
                brandDto.setThumbnail(brand.getThumbnail());
                return brandDto;
            }
        });

            return brandDtos;
    }



    @Override
    public BrandDto getBrandById(int id) {
        Optional<Brand> brand = brandRepository.findById(id);
        BrandDto brandDto = new BrandDto();
        brandDto.setId(id);
        brandDto.setName(brand.get().getName());
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String strDate = formatter.format(brand.get().getCreatedDate());
//        brandDto.setCreatedDate(strDate);
        brandDto.setThumbnail(brand.get().getThumbnail());
        return brandDto;
    }

    @Override
    public Brand updateBrand(BrandForm brand, int id) throws ParseException {
        Optional<Brand> brand2 = brandRepository.findById(id);
        Brand brand1 = new Brand();
        brand1.setId(id);
        brand1.setName(brand.getName());
//        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(brand.getCreatedDate());
        brand1.setCreatedDate(brand2.get().getCreatedDate());
        brand1.setThumbnail(brand.getThumbnail());
        brandRepository.save(brand1);
        return brand1;
    }
    @Override
    public void deleteBrand(int id) {
        Optional<Brand> brand = brandRepository.findById(id);
        List<Product> product =  productRepository.findAll();
        for(int i =0 ; i < product.size();i++){
            if(product.get(i).getBrand().getName().equals(brand.get().getName())){
                throw new MyException("Không Thể Xóa Danh Mục !!!");
            }
        }
        if(brand.isEmpty()){
            throw new NotFoundException("No Brand is founded");
        }
        else{
            brandRepository.deleteById(id);
        }
    }

    @Override
    public Brand createBrand(Brand brand) {
        Brand brand1 = brandRepository.findByName(brand.getName());
        if(brand1 != null){
            throw new DuplicateRecordException("Tên Danh mục đã tồn tại");
        }else{
            Brand brand2 = new Brand();
            brand2.setName(brand.getName());
            brand2.setCreatedDate(new Date());
            brand2.setThumbnail(brand.getThumbnail());
            brandRepository.save(brand2);
            return brand2;
        }

    }

    @Override
    public List<Brand> getAllBrand() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }


}
