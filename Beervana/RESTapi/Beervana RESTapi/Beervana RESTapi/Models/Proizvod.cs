using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Proizvod
    {
        public Proizvod()
        {
            OmiljenoPivo = new HashSet<OmiljenoPivo>();
            ProizvodNaPromociji = new HashSet<ProizvodNaPromociji>();
            Recenzija = new HashSet<Recenzija>();
            StavkaDegustacijskogMenija = new HashSet<StavkaDegustacijskogMenija>();
        }

        public int IdProizvod { get; set; }
        public int IdKategorija { get; set; }
        public string NazivProizvoda { get; set; }
        public double CijenaProizvoda { get; set; }
        public double? PostotakAlkohola { get; set; }
        public string Okus { get; set; }
        public double? Litara { get; set; }
        public string ZemljaPorjekla { get; set; }
        public string Proizvodac { get; set; }
        public string DatumKreiranja { get; set; }

        public virtual Kategorija IdKategorijaNavigation { get; set; }
        public virtual ICollection<OmiljenoPivo> OmiljenoPivo { get; set; }
        public virtual ICollection<ProizvodNaPromociji> ProizvodNaPromociji { get; set; }
        public virtual ICollection<Recenzija> Recenzija { get; set; }
        public virtual ICollection<StavkaDegustacijskogMenija> StavkaDegustacijskogMenija { get; set; }
    }
}
