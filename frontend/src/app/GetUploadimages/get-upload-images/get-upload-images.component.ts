import { Component, OnInit } from "@angular/core";
import {HttpHeaders, HttpClient} from '@angular/common/http';
import { CommunService } from './../../services/commun.service';

@Component({
  selector: "app-get-upload-images",
  templateUrl: "./get-upload-images.component.html",
  styleUrls: ["./get-upload-images.component.css"],
})
export class GetUploadImagesComponent  {
  constructor(private httpClient: HttpClient,private com:CommunService) {}
  selectedFile: File
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName2: any;
  imageCategory: any;
  imageDetails: any;
  imageName:any;
  price

  //Gets called when the user selects an image
  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
  }

  //Gets called when the user clicks on submit to upload the image
  onUpload() {
    console.log(this.selectedFile);
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.


    const uploadImageData = new FormData();
    uploadImageData.append("imageFile",this.selectedFile);
    uploadImageData.append("categorie",this.imageCategory);
    uploadImageData.append("details",this.imageDetails);
    uploadImageData.append("imageName",this.imageName2);
    uploadImageData.append("price",this.price);


    let headers=new HttpHeaders({'authorization':'Bearer '+this.com.jwt})


    //Make a call to the Spring Boot Application to save the image
    this.httpClient
      .post("http://localhost:8084/products/upload",uploadImageData,{
        observe: "response",headers:headers
      })
      .subscribe((response) => {
        if (response.status == 200) {
          this.message = "Image uploaded successfully";
        } else {
          this.message = "Image not uploaded successfully";
        }
      });
  }

  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient
      .get("http://localhost:8084/image/get/" + this.imageName)
      .subscribe((res) => {
        this.retrieveResonse = res;
        this.base64Data = this.retrieveResonse.picByte;
        this.retrievedImage = "data:image/jpeg;base64," + this.base64Data;
      });
  }
}
