const fs = require("fs");

let datas = [];

for (let i = 0; i < 36; i++) {
  let data = {
    id: i,
    jenis: "petak",
    komplek: "A",
    nama: "Amerika",
    harga: 6000,
    harga_sewa: [300, 2000, 6000, 16000, 30000],
    harga_beli: [5000, 16000],
    aksi: "asset"
  };

  datas.push(data);
}

datas = JSON.stringify(datas);

fs.writeFile("data_petak.json", datas, "utf8", function(err) {
  if (err) {
    console.log("An error occured while writing JSON Object to File.");
    return console.log(err);
  }

  console.log("JSON file has been saved.");
});
