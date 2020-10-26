using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class DogadajNaLokaciji
    {
        public int LokacijaIdLokacija { get; set; }
        public int DogadajIdDogadaj { get; set; }
        public DateTime DatumDogadajaOd { get; set; }
        public DateTime DatumDogadajaDo { get; set; }

        public virtual Dogadaj DogadajIdDogadajNavigation { get; set; }
        public virtual Lokacija LokacijaIdLokacijaNavigation { get; set; }
    }
}
