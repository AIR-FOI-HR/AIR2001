using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Promocija
    {
        public Promocija()
        {
            ProizvodNaPromociji = new HashSet<ProizvodNaPromociji>();
            PromocijaNaLokaciji = new HashSet<PromocijaNaLokaciji>();
        }

        public int IdPromocija { get; set; }
        public string NazivPromocije { get; set; }
        public string OpisPromocije { get; set; }
        public int PrimjenjiviPopust { get; set; }
        public DateTime DatumOd { get; set; }
        public DateTime DatumDo { get; set; }
        public int IdKorisnik { get; set; }
        public DateTime? DatumKreiranjaPromocije { get; set; }

        public virtual Korisnik IdKorisnikNavigation { get; set; }
        public virtual ICollection<ProizvodNaPromociji> ProizvodNaPromociji { get; set; }
        public virtual ICollection<PromocijaNaLokaciji> PromocijaNaLokaciji { get; set; }
    }
}
