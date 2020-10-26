using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class ProizvodNaPromociji
    {
        public int PromocijaIdPromocija { get; set; }
        public int ProizvodIdProizvod { get; set; }

        public virtual Proizvod ProizvodIdProizvodNavigation { get; set; }
        public virtual Promocija PromocijaIdPromocijaNavigation { get; set; }
    }
}
