const fs = require("fs");

let datas = [];
let jenis = "chances";

for (let i = 0; i < 33; i++) {
  jenis = (i > 16) ? "bonus" : "chances";
  let data = {
    "id": i,
    "jenis": jenis,
    "ket": "lorem ipsum",
    "aksi": "bayar",
    "qty": 0,
    "posisi": 0
  };

  datas.push(data);
}

datas = JSON.stringify(datas);

fs.writeFile("kartu_bonus.json", datas, "utf8", function(err) {
  if (err) {
    console.log("An error occured while writing JSON Object to File.");
    return console.log(err);
  }

  console.log("JSON file has been saved.");
});
