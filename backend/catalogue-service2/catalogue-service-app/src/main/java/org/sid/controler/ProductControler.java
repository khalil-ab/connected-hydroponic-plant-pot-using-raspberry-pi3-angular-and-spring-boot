package org.sid.controler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "products")
public class ProductControler
{
@Autowired
	private ProductRepository pr;

    @PostMapping("upload")
    public HttpStatus uplaodImage(@RequestParam("imageFile") MultipartFile file,@RequestParam("categorie") String categorie,@RequestParam("details") String details,@RequestParam("imageName") String imageName,@RequestParam("price") double price) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Product pdt = new Product(imageName,details,categorie,compressBytes(file.getBytes()),price);
        pr.save(pdt);
        return HttpStatus.OK;
    }

    @GetMapping("/get/{Imgcat}")
    public Product getImageBy_its_category(@PathVariable("Imgcat") String Imgcat) throws IOException, InterruptedException {
        final Optional<Product> pdct = pr.findByName(Imgcat);
        Product pdt = new Product(pdct.get().getName(), pdct.get().getDetails(),pdct.get().getCategory(),
         decompressBytes(pdct.get().getPicByte()),pdct.get().getPrice());
        System.out.println(pdt.getName());

        return pdt;
    }

	@GetMapping("/{category}")
	public List<Product> find_Allimages_By_its_category(@PathVariable(name = "category") String category)throws IOException
	{
		return pr.findAllByCategory(category);
	}
	
	/*@GetMapping("/Products")
	public List<Product> find_Allimagesy()throws IOException
	{
		return pr.findAll();
	}*/

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) throws IOException{
        Deflater deflater = new Deflater(); 
        deflater.setInput(data);       
        deflater.finish();       
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);   
        byte[] buffer = new byte[1024]; 

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try {
            outputStream.close();
        } catch (IOException e) {}
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();

    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }

            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}